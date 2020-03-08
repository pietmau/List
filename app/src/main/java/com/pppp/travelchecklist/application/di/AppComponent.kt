package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.createlist.di.NewListSubComponent
import com.pppp.travelchecklist.createlist.di.NewListModule
import com.pppp.travelchecklist.edititem.di.EditItemComponent
import com.pppp.travelchecklist.edititem.di.EditItemModule
import com.pppp.travelchecklist.list.di.CheckListFragmentComponent
import com.pppp.travelchecklist.list.di.CheckListFragmentModule
import com.pppp.travelchecklist.list.di.ViewCheckListComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.login.di.LoginComponent
import com.pppp.travelchecklist.login.di.LoginModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.notifications.di.NotificationModule
import com.pppp.travelchecklist.notifications.di.NotificationSubComponent

interface AppComponent {
    fun with(module: LoginModule): LoginComponent
    fun with(module: ViewCheckListModule): ViewCheckListComponent
    fun with(module: EditItemModule): EditItemComponent
    fun with(module: NotificationModule): NotificationSubComponent
    fun with(module: CheckListFragmentModule): CheckListFragmentComponent
    fun inject(app: App)

    fun mainSubComponentFactory(): MainSubComponent.Factory

    fun newListSubComponentFactory(): NewListSubComponent.Factory
}