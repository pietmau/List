package com.pppp.travelchecklist.notifications.di

import android.content.Context
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.AlarmIntentMaker

import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.AlarmIntentMakerImpl
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.FirebaseItemsProvider
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.FirebaseUserCheckListsRepository
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.ItemsProvider
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import com.pppp.travelchecklist.notifications.notificationissuer.NotificationIntentMaker
import com.pppp.travelchecklist.notifications.notificationissuer.NotificationIntentMakerImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
object NotificationModule {

    @JvmStatic
    @Provides
    fun provideAnalytics(logger: AnalyticsLogger): NotificationsAnalyticsLogger = logger

    @JvmStatic
    @Provides
    fun provideIntentMaker(context: Context): AlarmIntentMaker = AlarmIntentMakerImpl(context)

    @JvmStatic
    @Provides
    fun provideItemsProvider(): ItemsProvider = FirebaseItemsProvider()

    @JvmStatic
    @Provides
    fun provideUserCheckListsRepository(): UserCheckListsRepository = FirebaseUserCheckListsRepository()

    @JvmStatic
    @Provides
    @Named("IO")
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @JvmStatic
    @Provides
    fun provideNotificationIntentMaker(notificationIntentMaker: NotificationIntentMakerImpl): NotificationIntentMaker = notificationIntentMaker

}