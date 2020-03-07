package com.pppp.travelchecklist.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.item.CardItemView

class ItemsCardImpl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), ItemsCard {
    override lateinit var callback: CardItemView.Callback

    init {
        orientation = VERTICAL
    }

    override var items: List<CheckListItem>
        set(value) {
            populate(value)
        }
        get() = throw UnsupportedOperationException()

    private fun populate(value: List<CheckListItem>) {
        removeAllViews()
        value.dropLast(1).forEach {
            addView(createCardItemView(it))
            addView(createSeparator())
        }
        value.lastOrNull()?.let {
            addView(createCardItemView(it))
        }
    }

    private fun createSeparator(): View {
        val view = View(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 1)
            val color = ResourcesCompat.getColor(resources, R.color.gray, context.theme)
            setBackgroundColor(color)
        }
        return view
    }

    private fun createCardItemView(it: CheckListItem): CardItemView {
        val cardItemView = CardItemView(context).apply {
            callback = this@ItemsCardImpl.callback
            itemId = it.id
            data = it
        }
        return cardItemView
    }

}