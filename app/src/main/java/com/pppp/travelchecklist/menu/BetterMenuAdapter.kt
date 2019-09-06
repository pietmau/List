package com.pppp.travelchecklist.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pppp.travelchecklist.R

class BetterMenuAdapter(private val items: List<BetterMenuItem>, private val callback: Callback) : RecyclerView.Adapter<BetterMenuViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetterMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            0 -> inflater.inflate(R.layout.item_better_menu, parent, false)
            else -> throw IndexOutOfBoundsException()
        }
        return BetterMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BetterMenuViewHolder, position: Int) {
        holder.bind(items[position]) {
            callback.onItemClicked(items[it])
        }
    }

    override fun getItemViewType(position: Int) = items[position].type.value

    interface Callback {
        fun onItemClicked(item: BetterMenuItem)
    }
}

