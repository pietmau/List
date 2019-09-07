package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.Consumer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
object EditCardModule {

    @Provides
    @JvmStatic
    fun provideConsumer(presenter: EditCardPresenter): Consumer<EditCardPresenter.ViewAction> = presenter

    @Singleton
    @Provides
    @JvmStatic
    fun provideEditCardPresenter(): EditCardPresenter = EditCardPresenter()

}