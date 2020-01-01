package com.pppp.travelchecklist.edititem.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.model.EditItemModel
import com.pppp.travelchecklist.edititem.model.FireBaseEditItemModel
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.DateAndTimeFormatterImpl
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.DateAndTimeProviderImpl
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemMapper
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTravelViewModel
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@EditItemScope
@Module
class EditItemModule(
    private val fragment: Fragment,
    private val listId: String,
    private val categoryId: String,
    private val itemId: String
) {

    @Provides
    fun provideViewStatesProducer(editItemViewModel: EditItemTravelViewModel): ViewStatesProducer<EditItemViewState> = editItemViewModel

    @Provides
    fun provideViewIntentConsumer(editItemViewModel: EditItemTravelViewModel): ViewActionsConsumer<EditItemViewIntent> = editItemViewModel

    @Provides
    fun provideViewTransientEventProducer(editItemViewModel: EditItemTravelViewModel): TransientEventsProducer<EditItemTransientEvent> = editItemViewModel

    @EditItemScope
    @Provides
    fun provideEditItemViewModel(model: EditItemModel, resources: ResourcesWrapper): EditItemTravelViewModel = ViewModelProvider(
        fragment,
        Factory(listId, categoryId, itemId, model, resources)
    ).get(EditItemTravelViewModel::class.java)

    @Provides
    fun provideEditItemModel(): EditItemModel = FireBaseEditItemModel(singleCheckListRepository = FirebaseSingleCheckListRepository())
}

class Factory(
    private val listId: String,
    private val categoryId: String,
    private val itemId: String,
    val model: EditItemModel,
    val resources: ResourcesWrapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditItemTravelViewModel(listId, categoryId, itemId, model, EditItemMapper(DateAndTimeFormatterImpl(resources)), DateAndTimeProviderImpl()) as T
    }
}