package com.pppp.travelchecklist.list.view.card

import android.content.Context
import androidx.cardview.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.card.item.CardItemView
import kotlinx.android.synthetic.main.custom_check_list_card.view.cardItems
import kotlinx.android.synthetic.main.header_layout.view.title
import kotlin.properties.Delegates
import android.R.attr.right
import android.R.attr.left
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.header_layout.view.overflow

class CheckListCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val ELEVATION = 8F
    private val RADIUS = 12F
    private var cardId: Long by Delegates.notNull()
    lateinit var callback: Callback

    private val cardItemCallback = object : CardItemView.Callback {
        override fun onItemMoved(fromPosition: Int, toPosition: Int) {
            callback.onItemMoved(cardId, fromPosition, toPosition)
        }

        override fun onItemChecked(itemId: Long, checked: Boolean) {
            callback.onItemChecked(cardId, itemId, checked)
        }

        override fun onDeleteRequested(itemId: Long, data: CheckListItem) {
            callback.onItemDeleteRequested(cardId, itemId, data)

        }
    }

    init {
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
        val params = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(10, 10, 10, 0)
        setLayoutParams(params)
        val color = ContextCompat.getColor(context, R.color.green_super_super_light)
        setCardBackgroundColor(color)
        overflow.setOnClickListener {
            callback.onCardOptionsClicked(cardId)
        }
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

        fun onItemChecked(cardId: Long, itemId: Long, checked: Boolean)

        fun onItemMoved(cardId: Long, fromPosition: Int, toPosition: Int)

        fun onCardOptionsClicked(cardId: Long)
    }

}