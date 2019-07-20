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
    private var position: Int? = null
    private var callback: Callback? = null
    private lateinit var category: Category

    private val cardItemCallback = object : CardItem.Callback {
        override fun onDeleteRequested(itemId: Long, data: CheckListItem) {
            callback?.onItemDeleteRequested(this@CheckListCard.category.id, itemId, data)
        }

        override fun onSettingsRequested(itemId: Long, data: CheckListItem) {
            callback?.onItemSettingsRequested(this@CheckListCard.category.id, itemId, data)
        }
    }

    init {
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
    }

    fun bind(category: Category, position: Int, callback: Callback) {
        this.category = category
        this.position = position
        this.callback = callback
        content.removeAllViews()
        for ((index, value) in category.items.withIndex()) {
            content.addView(CardItem(context, value, index, cardItemCallback))
        }
    }

    interface Callback {
        fun onItemDeleteRequested(cardId: Long, itemPosition: Long, data: CheckListItem)

        fun onItemSettingsRequested(cardId: Long, itemPosition: Long, data: CheckListItem)
    }

}