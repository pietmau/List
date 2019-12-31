package com.pppp.travelchecklist.edititem.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewModel
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EditItemModule(private val fragment: Fragment) {

    @Provides
    fun provideViewStatesProducer(): ViewStatesProducer<EditItemViewState> = editItemViewModel()

    @Provides
    fun provideViewIntentConsumer(): ViewActionsConsumer<EditItemViewIntent> = editItemViewModel()

    private fun editItemViewModel() = ViewModelProvider(fragment).get(EditItemViewModel::class.java)
}