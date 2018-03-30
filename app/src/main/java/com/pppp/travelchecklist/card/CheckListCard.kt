package com.pppp.travelchecklist.card

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.carditem.CardItem
import com.pppp.travelchecklist.model.CheckListItemData
import kotlinx.android.synthetic.main.custom_check_list_card.view.*

class CheckListCard(
        context: Context,
        attrs: AttributeSet?
) : CardView(context) {
    private val ELEVATION = 8F
    private val RADIUS = 10F
    private var position: Int? = null
    private var callback: Callback? = null

    private val cardItemCallback = object : CardItem.Callback {
        override fun onDeleteRequested(position: Int, data: CheckListItemData) {
            callback?.onItemDeleteRequested(this@CheckListCard.position!!, position, data)
        }

        override fun onSettingsRequested(position: Int) {
            callback?.onItemSettingsRequested(this@CheckListCard.position!!, position)
        }
    }

    init {
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
    }

    fun bind(data: List<CheckListItemData>, position: Int, callback: Callback?) {
        this.position = position
        this.callback = callback
        content.removeAllViews()
        for ((index, value) in data.withIndex()) {
            content.addView(CardItem(context, value, index, cardItemCallback))
        }
    }

    interface Callback {
        fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int, data: CheckListItemData)
        fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int)
    }

}