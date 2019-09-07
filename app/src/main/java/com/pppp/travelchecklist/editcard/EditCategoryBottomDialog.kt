package com.pppp.travelchecklist.editcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.appComponent
import com.pppp.travelchecklist.getLong
import com.pppp.travelchecklist.getString
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.menu.BetterMenuItem
import com.pppp.travelchecklist.showDialog
import kotlinx.android.synthetic.main.fragment_dialog_editcategory.menu
import javax.inject.Inject

class EditCategoryBottomDialog : BottomSheetDialogFragment() {
    private val ADD = "add"
    private val DELETE = "delete"

    internal lateinit var consumer: Consumer<EditCardPresenter.ViewAction>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_editcategory, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditCategoryBottomDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menu.items = createOptions()
        menu.callback = { item ->
            when (item.id) {
                ADD -> TODO()
                DELETE -> onDeleteClicked()
            }
        }
    }

    private fun onDeleteClicked() {
        showDialog(title = R.string.delete_card, message = R.string.do_you_want_to_delete) {
            val listId = getString(LIST_ID) ?: return@showDialog
            val categoryId = getLong(CATEGORY_ID) ?: return@showDialog
            consumer.accept(EditCardPresenter.ViewAction.DeleteCard(listId, categoryId))
        }
    }

    private fun createOptions(): List<BetterMenuItem> =
        listOf(
            BetterMenuItem(id = ADD, title = resources.getString(R.string.add_item), icon = R.drawable.ic_add_box_24px),
            BetterMenuItem(id = DELETE, title = resources.getString(R.string.delete_card), icon = R.drawable.ic_delete_24px)
        )

    companion object {
        fun newInstance(listId: String, categoryId: Long) =
            EditCategoryBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putLong(CATEGORY_ID, categoryId)
                }
            }

        val LIST_ID = "list_id"
        val TAG = EditCategoryBottomDialog::class.simpleName!!
        val CATEGORY_ID = "category_id"
    }
}