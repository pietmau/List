package com.pppp.travelchecklist.notifications.di

import com.pppp.travelchecklist.notifications.bootreceiver.BootReceiver
import dagger.Subcomponent

@Subcomponent(modules = [NotificationModule::class])
interface NotificationSubComponent {
    fun inject(bootReceiver: BootReceiver)
}