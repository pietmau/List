package com.pppp.mapper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import kotlinx.android.synthetic.main.row.view.*

class ItemsTagsAdapter(
    private val items: List<CheckListItem>,
    private val tags: List<TagImpl>,
    private val callback: (CheckListItem, List<Tag>) -> Unit
) : RecyclerView.Adapter<ItemTagsHolder>() {
    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ItemTagsHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row, viewGroup, false)
        return ItemTagsHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTagsHolder, position: Int) {
        holder.bind(items[position], callback, tags)
    }

}

class ItemTagsHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        checkListItemImpl: CheckListItem,
        callback: (CheckListItem, List<Tag>) -> Unit,
        tags: List<TagImpl>
    ) {
        itemView.textview.text = checkListItemImpl.title
        itemView.setOnClickListener { callback(checkListItemImpl, tags) }
    }
}
