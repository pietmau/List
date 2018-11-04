package com.pppp.uploader.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.uploader.R

class CategoryAdapter(var items: List<CategoryImpl>, private val listener: (CategoryImpl) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class CategoryHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val textView: TextView

        init {
            textView = v.findViewById<TextView>(R.id.text)
        }

        fun bind(category: CategoryImpl, listener: (CategoryImpl) -> Unit) {
            textView.tag = category
            textView.setText(category.title)
            textView.setOnClickListener { listener.invoke(textView.tag as CategoryImpl) }
        }
    }
}