package com.pppp.travelchecklist.list.di

import com.pppp.travelchecklist.editcard.AddItemBottomDialog
import com.pppp.travelchecklist.editcard.EditCardModule
import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.editcard.EditCategoryBottomDialog
import com.pppp.travelchecklist.list.view.CheckListFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [ViewCheckListModule::class, EditCardModule::class])
interface ViewCheckListComponent {

    fun inject(viewCheckListFragment: AddCategoryBottomDialog)

    fun inject(editCategoryBottomDialog: EditCategoryBottomDialog)

    fun inject(addItemBottomDialog: AddItemBottomDialog)
}