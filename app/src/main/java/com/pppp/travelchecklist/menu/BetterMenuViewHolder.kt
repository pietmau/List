package com.pppp.travelchecklist.menu

import android.graphics.drawable.Icon
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.geColorFromTheme

class BetterMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val title: CheckedTextView
    private val context = itemView.context
    private val resources = context.resources

    init {
        title = view.findViewById(R.id.design_menu_item_text)
    }

    fun bind(betterMenuItem: BetterMenuItem, callback: (Int) -> Unit) {
        title.text = betterMenuItem.title
        itemView.setOnClickListener {
            callback(adapterPosition)
        }
        setUpIcon(betterMenuItem)
        title.setTextColor(getCofflor(betterMenuItem.selected))
    }

    private fun getCofflor(selected: Boolean) =
        if (selected) context.geColorFromTheme(android.R.attr.textColorPrimary) else context.geColorFromTheme(android.R.attr.textColorSecondary)

    private fun setUpIcon(betterMenuItem: BetterMenuItem) {
        betterMenuItem.icon?.let {
            val icon = ResourcesCompat.getDrawable(resources, it, context.theme)
            val iconSize = context.resources.getDimensionPixelSize(com.google.android.material.R.dimen.design_navigation_icon_size)
            icon?.setBounds(0, 0, iconSize, iconSize)
            TextViewCompat.setCompoundDrawablesRelative(title, icon, null, null, null)
        }
    }

}