package com.pppp.travelchecklist.editcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.utils.getStringArgument
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.menu.BetterMenuItem
import com.pppp.travelchecklist.utils.requireStringArgument
import com.pppp.travelchecklist.utils.showDialog
import kotlinx.android.synthetic.main.fragment_dialog_editcategory.menu
import javax.inject.Inject

class EditCategoryBottomDialog : BottomSheetDialogFragment() {
    private val ADD = "add"
    private val DELETE = "delete"

    @Inject
    internal lateinit var editCardViewActionsConsumer: ViewActionsConsumer<EditCardViewIntent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_editcategory, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.viewCheckListComponentFactory?.create()?.inject(this@EditCategoryBottomDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menu.items = createOptions()
        menu.callback = { item ->
            when (item.id) {
                ADD -> onAddClickad()
                DELETE -> onDeleteClicked()
            }
        }
    }

    private fun onAddClickad() {
        AddItemBottomDialog.newInstance(getStringArgument(LIST_ID)!!, requireStringArgument(CATEGORY_ID))
            .show(requireFragmentManager(), AddCategoryBottomDialog.TAG)
        dismiss()
    }

    private fun onDeleteClicked() {
        showDialog(title = R.string.delete_card, message = R.string.do_you_want_to_delete) {
            emit(EditCardViewIntent.DeleteCard(getStringArgument(LIST_ID)!!, requireStringArgument(CATEGORY_ID)))
        }
    }

    private fun emit(deleteCard: EditCardViewIntent) {
        editCardViewActionsConsumer.accept(deleteCard)
        dismiss()
    }

    private fun createOptions(): List<BetterMenuItem> =
        listOf(
            BetterMenuItem(id = ADD, title = resources.getString(R.string.add_item), icon = R.drawable.ic_add_box_24px),
            BetterMenuItem(id = DELETE, title = resources.getString(R.string.delete_card), icon = R.drawable.ic_delete_24px)
        )

    companion object {
        fun newInstance(listId: String, categoryId: String) =
            EditCategoryBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putString(CATEGORY_ID, categoryId)
                }
            }

        val LIST_ID = "list_id"
        val CATEGORY_ID = "categoryId"
    }
}