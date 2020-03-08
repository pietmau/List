package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.utils.UtilsModule
import dagger.Component

@Component(modules = [AppModule::class, UtilsModule::class])
interface AppComponentImpl : AppComponent