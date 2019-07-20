package com.pppp.travelchecklist.card

import android.content.Context
import androidx.cardview.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.carditem.CardItem
import kotlinx.android.synthetic.main.custom_check_list_card.view.*

class CheckListCard(
    context: Context, attrs: AttributeSet?
) : CardView(context) {
    private val ELEVATION = 8F
    private val RADIUS = 10F
    private var cardPosition: Int? = null
    private var callback: Callback? = null

    private val cardItemCallback = object : CardItem.Callback {
        override fun onDeleteRequested(itemPosition: Int, data: CheckListItem) {
            cardPosition?.let {
                callback?.onItemDeleteRequested(it, itemPosition, data)
            }
        }

        override fun onSettingsRequested(itemPosition: Int, data: CheckListItem) {
            cardPosition?.let {
                callback?.onItemSettingsRequested(it, itemPosition, data)
            }
        }
    }

    init {
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
    }

    fun bind(category: Category, position: Int, callback: Callback) {
        this.cardPosition = position
        this.callback = callback
        content.removeAllViews()
        for ((index, value) in category.items.withIndex()) {
            content.addView(CardItem(context, value, index, cardItemCallback))
        }
    }

    interface Callback {
        fun onItemDeleteRequested(cardId: Int, itemPosition: Int, data: CheckListItem)

        fun onItemSettingsRequested(cardId: Int, itemPosition: Int, data: CheckListItem)
    }

}