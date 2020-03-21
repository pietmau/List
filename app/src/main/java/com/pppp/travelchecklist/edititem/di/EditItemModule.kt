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
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class EditItemModule() {

    @Binds
    abstract fun provideViewStatesProducer(editItemViewModel: EditItemTravelViewModel): ViewStatesProducer<EditItemViewState>

    @Binds
    abstract fun provideViewIntentConsumer(editItemViewModel: EditItemTravelViewModel): ViewActionsConsumer<EditItemViewIntent>

    @Binds
    abstract fun provideViewTransientEventProducer(editItemViewModel: EditItemTravelViewModel): TransientEventsProducer<EditItemTransientEvent>

    companion object {

        @JvmStatic
        @EditItemScope
        @Provides
        fun provideEditItemViewModel(
            model: EditItemModel, resources: ResourcesWrapper,
            fragment: Fragment
        ): EditItemTravelViewModel = ViewModelProvider(
            fragment,
            Factory(model, resources)
        ).get(EditItemTravelViewModel::class.java)

        @JvmStatic
        @Provides
        fun provideEditItemModel(@Named(LIST_ID) listId: String, @Named(CATEGORY_ID) categoryId: String, @Named(ITEM_ID) itemId: String): EditItemModel =
            FireBaseEditItemModel(listId = listId, categoryId = categoryId, itemId = itemId)
    }
}

class Factory(val model: EditItemModel, val resources: ResourcesWrapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditItemTravelViewModel(model, EditItemMapper(DateAndTimeFormatterImpl(resources), DateAndTimeProviderImpl())) as T
    }
}