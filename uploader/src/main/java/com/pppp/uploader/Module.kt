package com.pppp.uploader

import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.CheckListDatabase
import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun provideDatabse(): com.pppp.travelchecklist.selector.view.viewpager.fragments.models.CheckListDatabase =
        CloudFirestoreCheckListDatabase()
}