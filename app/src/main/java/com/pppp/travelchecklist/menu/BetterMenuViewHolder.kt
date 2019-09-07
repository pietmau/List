package com.pppp.travelchecklist.menu

import android.graphics.drawable.Icon
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pppp.travelchecklist.R

class BetterMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView
    private val icon: ImageView

    init {
        title = view.findViewById(R.id.title)
        icon = view.findViewById(R.id.icon)
    }

    fun bind(betterMenuItem: BetterMenuItem, callback: (Int) -> Unit) {
        title.text = betterMenuItem.title
        seticon(betterMenuItem)
        itemView.isSelected = betterMenuItem.selected
        itemView.setOnClickListener {
            callback(adapterPosition)
        }
    }

    private fun seticon(betterMenuItem: BetterMenuItem) {
        if (betterMenuItem.icon != null) {
            icon.visibility = VISIBLE
            icon.setImageResource(betterMenuItem.icon)
        } else {
            icon.visibility = GONE
        }
    }
}