package com.pppp.uploader.activities

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.uploader.R

class ItemsAdapter(
    private val items: List<CheckListItemImpl>,
    private val listener: (CheckListItemImpl) -> Unit
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flex_item, parent, false)
        return ItemHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ItemHolder(private val textView: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(textView) {

        fun bind(item: CheckListItemImpl, listener: (CheckListItemImpl) -> Unit) {
            textView.tag = item
            textView.setText(item.title)
            textView.setOnClickListener { listener.invoke(textView.tag as CheckListItemImpl) }
        }
    }

}