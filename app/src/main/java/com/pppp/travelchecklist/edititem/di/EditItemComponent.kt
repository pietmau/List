package com.pppp.travelchecklist.edititem.di

import com.pppp.travelchecklist.edititem.view.EditItemDialogFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = arrayOf(EditItemModule::class))
interface EditItemComponent {

    fun inject(editItemDialogFragment: EditItemDialogFragment)
}