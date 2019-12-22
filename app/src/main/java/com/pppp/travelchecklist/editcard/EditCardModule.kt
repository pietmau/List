package com.pppp.travelchecklist.editcard

import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDao
import com.pppp.travelchecklist.repository.room.RoomTravelChecklistRepositoryDatabase
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
    fun provideEditCardPresenter(database: RoomTravelChecklistRepositoryDao): EditCardViewModel = TODO()//EditCardViewModel(roomTravelChecklistRepositoryDao)

}