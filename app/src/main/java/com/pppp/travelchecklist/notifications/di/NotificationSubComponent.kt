package com.pppp.travelchecklist.notifications.di

import com.pppp.travelchecklist.notifications.BootReceiver
import dagger.Subcomponent

@Subcomponent(modules = [NotificationModule::class])
interface NotificationSubComponent {
    fun inject(bootReceiver: BootReceiver)
}