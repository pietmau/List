package com.pppp.travelchecklist.list.di

import androidx.fragment.app.FragmentActivity
import com.pppp.travelchecklist.editcard.AddItemBottomDialog
import com.pppp.travelchecklist.editcard.EditCardModule
import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.editcard.EditCategoryBottomDialog
import com.pppp.travelchecklist.list.view.CheckListFragment
import com.pppp.travelchecklist.login.di.LoginComponent
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [ViewCheckListModule::class, EditCardModule::class])
interface ViewCheckListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewCheckListComponent
    }

    fun inject(viewCheckListFragment: AddCategoryBottomDialog)

    fun inject(editCategoryBottomDialog: EditCategoryBottomDialog)

    fun inject(addItemBottomDialog: AddItemBottomDialog)
}