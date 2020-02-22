package com.pppp.travelchecklist.notifications.di

import android.content.Context
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.notifications.notificationsetter.intentmaker.IntentMaker
import com.pppp.travelchecklist.notifications.notificationsetter.intentmaker.IntentMakerImpl
import com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider.FirebaseItemsProvider
import com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider.ItemsProvider
import dagger.Module
import dagger.Provides

@Module
object NotificationModule {

    @JvmStatic
    @Provides
    fun provideAnalytics(logger: AnalyticsLogger): NotificationsAnalyticsLogger = logger

    @JvmStatic
    @Provides

    @JvmStatic
    @Provides
    fun provideIntentMaker(context: Context): IntentMaker = IntentMakerImpl(context)
}