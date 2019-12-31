package com.pppp.travelchecklist.edititem.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.model.EditItemModel
import com.pppp.travelchecklist.edititem.model.FireBaseEditItemModel
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemMapper
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewModel
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.repository.SingleCheckListRepository
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
    fun provideViewStatesProducer(editItemViewModel: EditItemViewModel): ViewStatesProducer<EditItemViewState> = editItemViewModel

    @Provides
    fun provideViewIntentConsumer(editItemViewModel: EditItemViewModel): ViewActionsConsumer<EditItemViewIntent> = editItemViewModel

    @EditItemScope
    @Provides
    fun provideEditItemViewModel(model: EditItemModel): EditItemViewModel = ViewModelProvider(fragment, Factory(listId, categoryId, itemId, model)).get(
        EditItemViewModel::class.java
    )

    @Provides
    fun provideEditItemModel(): EditItemModel = FireBaseEditItemModel(singleCheckListRepository = FirebaseSingleCheckListRepository())
}

class Factory(
    private val listId: String,
    private val categoryId: String,
    private val itemId: String,
    val model: EditItemModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditItemViewModel(listId, categoryId, itemId, model, EditItemMapper) as T
    }
}