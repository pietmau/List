package com.pppp.travelchecklist.card

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.carditem.CardItem
import com.pppp.travelchecklist.model.CheckListItemData
import kotlinx.android.synthetic.main.custom_check_list_card.view.*

class CheckListCard @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val ELEVATION = 8F
    private val RADIUS = 10F

    init {
        //TODO use custom attributes
        elevation = ELEVATION
        radius = RADIUS
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card, this, true)
    }

    fun setItems(data: List<CheckListItemData>) {
        content.removeAllViews()
        data.map { content.addView(CardItem(context, data = it)) }
    }
}