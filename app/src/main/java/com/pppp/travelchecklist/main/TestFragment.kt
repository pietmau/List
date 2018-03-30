package com.pppp.travelchecklist.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.SimpleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_blank.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class TestFragment : Fragment(), TravelListView {
    @Inject lateinit var presenter: MainPresenter

    private var disposable = CompositeDisposable()

    private val actions = PublishSubject.create<TravelListView.Action>()

    private val callback = object : CheckListCard.Callback {
        override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int) {
            actions.onNext(TravelListView.Action.DeleteRequest(TravelListView.Action.Position(cardPosition, itemPosition)))
        }

        override fun onItemEditRequested(cardPosition: Int, itemPosition: Int) {
            actions.onNext(TravelListView.Action.EditRequest(TravelListView.Action.Position(cardPosition, itemPosition)))
        }
    }

    override fun render(viewConfiguration: TravelListView.ViewConfiguration) {
        recycler.setItems(viewConfiguration.items)
        when (viewConfiguration) {
            is TravelListView.ViewConfiguration.DeleteRequest -> delete(viewConfiguration)
        }
    }

    private fun delete(viewConfiguration: TravelListView.ViewConfiguration.DeleteRequest) {
        activity?.alert(Appcompat,
                getString(R.string.delete),
                getString(R.string.delete) + " " + viewConfiguration.item.title + "?") {
            noButton { }
            yesButton { presenter.deleteItem(viewConfiguration.position) }
        }?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this@TestFragment.activity as AppCompatActivity
        (activity?.application as? App)?.appComponent?.with(MainModule(activity))?.inject(this@TestFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onPause() {
        super.onPause()
        recycler.callback = null
        if (disposable?.isDisposed == false) {
            disposable?.dispose()
        }
    }

    override fun onResume() {
        super.onResume()
        recycler.callback = callback
        disposable.add(presenter.observe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith<SimpleObserver<TravelListView.ViewConfiguration>>(object : SimpleObserver<TravelListView.ViewConfiguration>() {
                    override fun onNext(viewConfiguration: TravelListView.ViewConfiguration) {
                        render(viewConfiguration)
                    }
                }))
        disposable.add(presenter.subscribe(actions.subscribeOn(Schedulers.io())))
    }

    companion object {
        val TAG = TestFragment::class.simpleName
        fun newInstance(): TestFragment = TestFragment()
    }
}
