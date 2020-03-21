package com.pppp.travelchecklist.edititem.di

import androidx.fragment.app.Fragment
import com.pppp.travelchecklist.edititem.view.EditItemDialogFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

const val LIST_ID = "list_id"
const val CATEGORY_ID = "category_id"
const val ITEM_ID = "item_id"

@EditItemScope
@Subcomponent(modules = [EditItemModule::class])
interface EditItemComponent {

    fun inject(editItemDialogFragment: EditItemDialogFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance @Named(LIST_ID) listId: String,
            @BindsInstance @Named(CATEGORY_ID) categoryId: String,
            @BindsInstance @Named(ITEM_ID) itemId: String
        ): EditItemComponent
    }
}