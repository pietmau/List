package com.pppp.travelchecklist.application.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.database.DestinationPresenter
import com.pppp.travelchecklist.database.DestinationPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideTravelChecklistDatabase(firebaseDatabase: FirebaseDatabase): DestinationPresenter =
        DestinationPresenterImpl(
            firebaseDatabase,
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )

    @Provides
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    fun provideContext() = context

}