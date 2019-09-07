package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.Consumer
import dagger.Module
import dagger.Provides

@Module
object EditCardModule {

    @Provides
    @JvmStatic
    fun provideConsumer(presenter: EditCardPresenter): Consumer<EditCardPresenter.ViewAction> = presenter
}