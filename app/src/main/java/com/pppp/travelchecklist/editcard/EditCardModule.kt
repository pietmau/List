package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewActionsConsumer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
object EditCardModule {

    @Provides
    @JvmStatic
    fun provideConsumer(viewModel: EditCardViewModel): ViewActionsConsumer<EditCardViewAction> = viewModel

    @Singleton
    @Provides
    @JvmStatic
    fun provideEditCardPresenter(): EditCardViewModel = EditCardViewModel()

}