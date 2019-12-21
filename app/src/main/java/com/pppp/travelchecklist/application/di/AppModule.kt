package com.pppp.travelchecklist.application.di

import android.content.Context
import android.preference.PreferenceManager
import androidx.room.Room
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.analytics.FirebaseAnalyticsLogger
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.customanalytics.CustomAnalytics
import com.pppp.travelchecklist.analytics.customanalytics.IftttCustomAnalyticsClient
import com.pppp.travelchecklist.analytics.customanalytics.IftttAnalytics
import com.pppp.travelchecklist.createlist.model.models.InitialTagsRepository
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.api.RetrofitClient
import com.pppp.travelchecklist.database.DestinationPresenter
import com.pppp.travelchecklist.database.DestinationPresenterImpl
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.listgenerator.ListGeneratorImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.createlist.model.models.InitialTagsRepositoryImpl
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.PreferencesWrapperImpl
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepository
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class AppModule(private val context: Context, private val firebaseAnalytics: FirebaseAnalytics) {

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
    fun provideListGenarator(
        db: InitialTagsRepository,
        client: Client,
        travelChecklistRepository: TravelChecklistRepository
    ): ListGenerator = ListGeneratorImpl(client, travelChecklistRepository, AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun provideListRepository(database: RoomTravelChecklistRepositoryDatabase): TravelChecklistRepository = RoomTravelChecklistRepository(database)

    @Provides
    fun provideDb(client: Client): InitialTagsRepository = InitialTagsRepositoryImpl(client)

    @Provides
    fun provideLogger(prefs: PreferencesWrapper, customAnalytics: CustomAnalytics): AnalyticsLogger =
        FirebaseAnalyticsLogger(firebaseAnalytics, customAnalytics)

    @Provides
    fun provideCustomAnalytics(wrapper: PreferencesWrapper): CustomAnalytics = IftttAnalytics(
        context,
        IftttCustomAnalyticsClient(BuildConfig.ifttt_key),
        wrapper
    )

    @Provides
    fun providePreferences(): PreferencesWrapper = PreferencesWrapperImpl(PreferenceManager.getDefaultSharedPreferences(context))

    @Provides
    fun database(): RoomTravelChecklistRepositoryDatabase = Room
        .databaseBuilder(context.applicationContext, RoomTravelChecklistRepositoryDatabase::class.java, "list_database")
        .build()

    companion object {
        private const val URL = "https://sj9qwuk05k.execute-api.eu-west-1.amazonaws.com/"
    }

}