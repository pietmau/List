package com.pppp.uploader.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pppp.entities.CheckListItem
import com.pppp.uploader.R

class ItemsAdapter(
    private val items: List<CheckListItem>,
    private val listener: (CheckListItem) -> Unit
) :
    RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flex_item, parent, false)
        return ItemHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ItemHolder(private val textView: TextView) : RecyclerView.ViewHolder(textView) {

        fun bind(item: CheckListItem, listener: (CheckListItem) -> Unit) {
            textView.tag = item
            textView.setText(item.title)
            textView.setOnClickListener { listener.invoke(textView.tag as CheckListItem) }
        }
    }

}