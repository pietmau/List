package com.pppp.travelchecklist.application.di

import android.content.Context
import android.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.analytics.FirebaseAnalyticsLogger
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.CustomAnalytics
import com.pppp.travelchecklist.analytics.CustomAnalyticsClientImpl
import com.pppp.travelchecklist.analytics.CustomAnalyticsImpl
import com.pppp.travelchecklist.newlist.model.models.InitialTagsRepository
import com.pppp.travelchecklist.api.Client
import com.pppp.travelchecklist.api.RetrofitClient
import com.pppp.travelchecklist.database.DestinationPresenter
import com.pppp.travelchecklist.database.DestinationPresenterImpl
import com.pppp.travelchecklist.repository.FirebaseTravelChecklistRepository
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.listgenerator.ListGeneratorImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.newlist.model.models.InitialTagsRepositoryImpl
import com.pppp.travelchecklist.prefeerences.PreferencesWrapper
import com.pppp.travelchecklist.prefeerences.PreferencesWrapperImpl
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
    fun provideListRepository(): TravelChecklistRepository = FirebaseTravelChecklistRepository()

    @Provides
    fun provideDb(client: Client): InitialTagsRepository = InitialTagsRepositoryImpl(client)

    @Provides
    fun provideLogger(prefs: PreferencesWrapper, customAnalytics: CustomAnalytics): AnalyticsLogger = FirebaseAnalyticsLogger(
        firebaseAnalytics,
        prefs,
        customAnalytics
    )

    @Provides
    fun provideCustomAnalytics(): CustomAnalytics = CustomAnalyticsImpl(context, CustomAnalyticsClientImpl("ezx1FbpWrA9uEoYuj0HXlz9i3gp64jRL00H-djnvJLS"))

    @Provides
    fun providePreferences(): PreferencesWrapper = PreferencesWrapperImpl(PreferenceManager.getDefaultSharedPreferences(context))

    companion object {
        private const val URL = "https://sj9qwuk05k.execute-api.eu-west-1.amazonaws.com/"
    }

}