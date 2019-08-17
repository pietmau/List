package com.pppp.travelchecklist.list.view.recycler.card

import android.content.Context
import androidx.cardview.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.recycler.card.recycler.CardItemView
import kotlinx.android.synthetic.main.custom_check_list_card.view.cardItems
import kotlinx.android.synthetic.main.header_layout.view.title

class CheckListCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val ELEVATION = 8F
    private val RADIUS = 15F
    private var cardId: Long? = null
    private var callback: Callback? = null

    private val cardItemCallback = object : CardItemView.Callback {
        override fun onDeleteRequested(itemId: Long, data: CheckListItem) {
            cardId?.let { cardId ->
                callback?.onItemDeleteRequested(cardId, itemId, data)
            }
        }

        override fun onSettingsRequested(itemId: Long, data: CheckListItem) {
            cardId?.let { cardId ->
                callback?.onItemSettingsRequested(cardId, itemId, data)
            }
        }
    }

    init {
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
        setBackgroundResource(R.color.very_light_blue)
    }

    fun bind(category: Category, callback: Callback) {
        this.cardId = category.id
        this.callback = callback
        cardItems.callback = cardItemCallback
        setItems(category.items)
        title.text = category.title
    }

    fun setItems(items: List<CheckListItem>) {
        cardItems.items = items
    }

    interface Callback {
        fun onItemDeleteRequested(cardId: Long, itemId: Long, data: CheckListItem)

        fun onItemSettingsRequested(cardId: Long, itemId: Long, data: CheckListItem)
    }

}