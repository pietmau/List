package com.pppp.uploader

import com.pppp.database.CheckListDatabase
import com.pppp.database.CloudFirestoreCheckListDatabase
import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun provideDatabse(): CheckListDatabase = CloudFirestoreCheckListDatabase()
}