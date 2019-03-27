package com.pppp.uploader.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pppp.entities.pokos.Tag
import com.pppp.uploader.R

class TagsAdapter(private val tags: List<Tag>, private val listener: (Tag) -> Unit) :
    RecyclerView.Adapter<TagsAdapter.TagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TagsHolder(view)
    }

    override fun getItemCount() = tags.size

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        holder.bind(tags[position], listener)
    }

    class TagsHolder(val v: View) : RecyclerView.ViewHolder(v) {
        private val tv = v.findViewById<TextView>(R.id.text)

        fun bind(tag: Tag, listener: (Tag) -> Unit) {
            v.tag = tag
            tv.text = tag.title
            v.setOnClickListener { view -> listener.invoke(view.tag as Tag) }
        }
    }
}