package com.pppp.travelchecklist.application.di

import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.database.TravelChecklistDatabase
import com.pppp.travelchecklist.database.TravelChecklistDatabaseImpl
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class AppModule {

    @Provides
    fun provideTravelChecklistDatabase(firebaseDatabase: FirebaseDatabase): TravelChecklistDatabase =
        TravelChecklistDatabaseImpl(firebaseDatabase, AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

}