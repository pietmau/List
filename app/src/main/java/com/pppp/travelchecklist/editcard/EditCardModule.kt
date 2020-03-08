package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewActionsConsumer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object EditCardModule {

    @Provides
    @JvmStatic
    fun provideConsumer(viewModel: EditCardViewModel): ViewActionsConsumer<EditCardViewIntent> = viewModel

    @Singleton
    @Provides
    @JvmStatic
    fun provideEditCardPresenter(): EditCardViewModel = EditCardViewModel()

}