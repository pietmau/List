package com.pppp.travelchecklist.list.view.recycler.card

import android.content.Context
import androidx.cardview.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.recycler.card.recycler.item.CardItemView
import kotlinx.android.synthetic.main.custom_check_list_card.view.cardItems

class CheckListCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val ELEVATION = 8F
    private val RADIUS = 10F
    private var cardPosition: Int? = null
    private var callback: Callback? = null

    private val cardItemCallback = object : CardItemView.Callback {
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
        cardItems.callback = cardItemCallback
        cardItems.items = category.items
    }

    interface Callback {
        fun onItemDeleteRequested(cardId: Int, itemPosition: Int, data: CheckListItem)

        fun onItemSettingsRequested(cardId: Int, itemPosition: Int, data: CheckListItem)
    }

}