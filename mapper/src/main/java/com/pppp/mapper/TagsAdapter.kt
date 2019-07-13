package com.pppp.mapper

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import kotlinx.android.synthetic.main.row.view.*

class TagsAdapter(
    private val tags: MutableList<Tag>,
    private val itemTags: MutableList<Tag>
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<TagHolder>() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TagHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row, viewGroup, false)
        return TagHolder(view)
    }

    private fun add(tag: Tag) {
        itemTags.add(tag)
    }

    private fun remove(tag: Tag) {
        itemTags.remove(itemTags.find { it.id == tag.id })
    }

    override fun getItemCount() = tags.count()

    override fun onBindViewHolder(tagHolder: TagHolder, position: Int) {
        val checkListItemImpl = tags[position]
        tagHolder.bind(checkListItemImpl, isSelected(checkListItemImpl)){ tag:Tag ->
            if (isSelected(tag)) remove(tag) else add(tag)
            notifyItemChanged(position)
        }
    }

    private fun isSelected(checkListItemImpl: Tag) =
        itemTags.find { it.id == checkListItemImpl.id } != null

    override fun onAttachedToRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun getTags()= itemTags
}

class TagHolder(private val view: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bind(
        tagImpl: Tag,
        selected: Boolean,
        function: (Tag) -> Unit
    ) {
        itemView.textview.text = tagImpl.title
        itemView.setBackgroundColor(if (selected) Color.YELLOW else Color.WHITE)
        itemView.setOnClickListener { function(tagImpl) }
    }
}