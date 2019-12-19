package com.pppp.travelchecklist.list.view.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.card.item.CardItemView
import kotlinx.android.synthetic.main.custom_check_list_card.view.cardItems
import kotlinx.android.synthetic.main.header_layout.view.title
import kotlin.properties.Delegates
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.header_layout.view.overflow

class NoCardCheckListCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), CheckListCard {
    private var cardId: String by Delegates.notNull()
    override lateinit var checkListCardCallback: ChackListCardCallback

    private val cardItemCallback = object : CardItemView.Callback {
        override fun onSettingsClicked(data: CheckListItem) {
            //checkListCardCallback.onSettingsClicked(cardId, data.id)
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int) {
            checkListCardCallback.onItemMoved(cardId, fromPosition, toPosition)
        }

        override fun onItemChecked(itemId: String, checked: Boolean) {
            checkListCardCallback.onItemChecked(cardId, itemId, checked)
        }

        override fun onDeleteRequested(itemId: String, data: CheckListItem) {
            checkListCardCallback.onItemDeleteRequested(cardId, itemId, data)
        }
    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.NoCardCheckListCard, 0, 0)
        val margin = typedArray.getDimensionPixelSize(R.styleable.NoCardCheckListCard_margin, 0)
        setMargins(margin)
        typedArray.recycle()
        overflow.setOnClickListener {
            checkListCardCallback.onCardOptionsClicked(cardId)
        }
    }

    private fun setMargins(margin: Int) {
        val params = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, margin, margin, 0)
        setLayoutParams(params)
    }

    override fun bind(category: Category, chackListCardCallback: ChackListCardCallback) {
        this.cardId = requireNotNull(category.id).toString()
        this.checkListCardCallback = chackListCardCallback
        cardItems.callback = cardItemCallback
        setItems(category.items)
        title.text = category.title
    }

    override fun setItems(items: List<CheckListItem>) {
        cardItems.items = items
    }

}