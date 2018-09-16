package com.pppp.uploader.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.pppp.uploader.R

class ItemsAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flex_item, parent, false)
        return ItemHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
    }

    class ItemHolder(private val textView: TextView) : RecyclerView.ViewHolder(textView) {

        fun bind(s: String) {
            textView.setText(s)
        }
    }

}