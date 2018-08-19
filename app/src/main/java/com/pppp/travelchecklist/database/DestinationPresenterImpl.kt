package com.pppp.travelchecklist.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject

class DestinationPresenterImpl(
    firebaseDatabase: FirebaseDatabase,
    val mainThreadScheduler: Scheduler,
    val differentThreadScheduler: Scheduler
) : DestinationPresenter {
    companion object {
        const val COUNTRIES = "countries"
    }

    private val databaseReference = firebaseDatabase.getReference(COUNTRIES)
    private var eventListener: ValueEventListener? = null
    private var subscription: Disposable? = null
    private var subject: PublishSubject<List<Country>>? = null

    override fun getCountries(observer: DisposableObserver<List<Country>>) {
        subject = PublishSubject.create()
        eventListener = databaseReference.addValueEventListener(object : SimpleValueEventListener() {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot
                    ?.children
                    ?.map { it.getValue(Country::class.java) }
                    ?.filterNotNull()
                    ?.let { subject?.onNext(it) }
            }
        })
        subscription = subject
            ?.cache()
            ?.subscribeOn(differentThreadScheduler)
            ?.observeOn(mainThreadScheduler)
            ?.subscribeWith(observer)
    }

    override fun unsubscribe() {
        eventListener?.let { databaseReference.removeEventListener(it) }
        subscription?.let {
            if (it.isDisposed) {
                it.dispose()
            }
        }
        subject = null
    }

}