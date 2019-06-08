package com.pppp.travelchecklist.application.di

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.api.RetrofitClient
import com.pppp.travelchecklist.database.DestinationPresenter
import com.pppp.travelchecklist.database.DestinationPresenterImpl
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.listgenerator.ListGeneratorImpl
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.RetrofitRepository
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

    @Provides
    fun provideClient(): Client = RetrofitClient(URL)

    @Provides
    fun provideListGenarator(db: CheckListDatabase, client: Client): ListGenerator = ListGeneratorImpl(db, client)

    @Provides
    fun provideDb(client: Client): CheckListDatabase = RetrofitRepository(client)

    companion object {
        private const val URL = "https://sj9qwuk05k.execute-api.eu-west-1.amazonaws.com/"
    }

}