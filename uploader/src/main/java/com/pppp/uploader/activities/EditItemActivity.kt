package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.pppp.entities.CheckListItem
import kotlinx.android.synthetic.main.add_items.*

class EditItemActivity : AddItemActivity() {

    override open fun onItemClicked(item: CheckListItem) {
        clearAll()
        name.setText(item.title, TextView.BufferType.EDITABLE)
        priorty.setText(item.title, TextView.BufferType.EDITABLE)
        description.setText(item.description, TextView.BufferType.EDITABLE)
        check.isChecked = item.optional
        categoriesSelected.clear()
        categoriesSelected.add(item.category)
        tagSelected.addAll(item.tags)
        renderTags()
        rendercategories()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, EditItemActivity::class.java)
            context.startActivity(intent)
        }
    }
}