package com.pppp.travelchecklist.notifications.di

import com.pppp.travelchecklist.notifications.alarmreceiver.AlarmReceiver
import com.pppp.travelchecklist.notifications.bootreceiver.BootReceiver
import dagger.Subcomponent

@Subcomponent(modules = [NotificationModule::class])
interface NotificationSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationSubComponent
    }

    fun inject(bootReceiver: BootReceiver)

    fun inject(bootReceiver: AlarmReceiver)
}