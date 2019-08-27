package com.pppp.travelchecklist.menu

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pppp.travelchecklist.R

class BetterMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView

    init {
        title = view.findViewById(R.id.title)
    }

    fun bind(betterMenuItem: BetterMenuItem, callback: (Int) -> Unit) {
        title.text = betterMenuItem.title
        title.setCompoundDrawablesRelativeWithIntrinsicBounds(betterMenuItem.icon ?: 0, 0, 0, 0)
        itemView.setOnClickListener {
            callback(adapterPosition)
        }
    }
}