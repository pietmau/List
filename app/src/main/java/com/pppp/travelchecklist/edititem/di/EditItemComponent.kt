package com.pppp.travelchecklist.edititem.di

import com.pppp.travelchecklist.edititem.view.EditItemDialogFragment
import dagger.Subcomponent

@EditItemScope
@Subcomponent(modules = arrayOf(EditItemModule::class))
interface EditItemComponent {

    fun inject(editItemDialogFragment: EditItemDialogFragment)
}