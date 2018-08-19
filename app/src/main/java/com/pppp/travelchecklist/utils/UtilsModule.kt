package com.pppp.travelchecklist.utils

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun provideResources(context: Context) = ResourcesWrapper(context.resources)
}