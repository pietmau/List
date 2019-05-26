package com.pppp.mapper

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.pokos.TagImpl
import kotlinx.android.synthetic.main.row.view.*

class TagsAdapter(
    private val tags: MutableList<TagImpl>,
    private val itemTags: MutableList<TagImpl>
) :
    RecyclerView.Adapter<TagHolder>() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TagHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row, viewGroup, false)
        return TagHolder(view)
    }

    private fun add(tag: TagImpl) {
        itemTags.add(tag)
    }

    private fun remosssve(tag: TagImpl) {
        itemTags.remove(itemTags.find { it.id == tag.id })
    }

    override fun getItemCount() = tags.count()

    override fun onBindViewHolder(tagHolder: TagHolder, position: Int) {
        val checkListItemImpl = tags[position]
        tagHolder.bind(checkListItemImpl, isSelected(checkListItemImpl)){ tag:TagImpl ->
            if (isSelected(tag)) remosssve(tag) else add(tag)
            notifyItemChanged(position)
        }
    }

    private fun isSelected(checkListItemImpl: TagImpl) =
        itemTags.find { it.id == checkListItemImpl.id } != null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun getTags()= itemTags
}

class TagHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(
        tagImpl: TagImpl,
        selected: Boolean,
        function: (TagImpl) -> Unit
    ) {
        itemView.textview.text = tagImpl.title
        itemView.setBackgroundColor(if (selected) Color.YELLOW else Color.WHITE)
        itemView.setOnClickListener { function(tagImpl) }
    }
}