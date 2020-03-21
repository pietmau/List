package com.pppp.travelchecklist.notifications.di

import android.content.Context
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.notifications.alarmsrepository.AlarmsRepository
import com.pppp.travelchecklist.notifications.alarmsrepository.FirebaseAlarmsRepository
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMaker

import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMakerImpl
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.FirebaseUserCheckListsRepository
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import com.pppp.travelchecklist.notifications.bootreceiver.BootReceiverModel
import com.pppp.travelchecklist.notifications.bootreceiver.FirebaseBootReceiverModel
import com.pppp.travelchecklist.notifications.notificationissuer.NotificationMaker
import com.pppp.travelchecklist.notifications.notificationissuer.NotificationMakerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
abstract class NotificationModule {

    @Binds
    abstract fun provideAnalytics(logger: AnalyticsLogger): NotificationsAnalyticsLogger

    @Binds
    abstract fun provideIntentMaker(maker: IntentMakerImpl): IntentMaker

    @Binds
    abstract fun provideNotificationIntentMaker(notificationIntentMaker: NotificationMakerImpl): NotificationMaker

    @Binds
    abstract fun provideBootReceiverModel(repo: FirebaseBootReceiverModel): BootReceiverModel

    companion object {

        @JvmStatic
        @Provides
        fun provideUserCheckListsRepository(): UserCheckListsRepository = FirebaseUserCheckListsRepository()

        @JvmStatic
        @Provides
        @Named("IO")
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @JvmStatic
        @Provides
        fun provideAlarmsRepository(): AlarmsRepository = FirebaseAlarmsRepository()
    }
}