package com.pppp.travelchecklist.selector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ToggleButton
import butterknife.BindView
import butterknife.ButterKnife
import com.pppp.travelchecklist.R


class WhoIsTravellingView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    var whoIsTravelling: List<WhoIsTravelling>
        get() {
            return getSelected()
        }
        set(newValue) {
            setSelected(newValue)
        }

    @BindView(R.id.male) lateinit var male: ToggleButton
    @BindView(R.id.fermale) lateinit var fermale: ToggleButton
    @BindView(R.id.babies) lateinit var babies: ToggleButton
    @BindView(R.id.toddlers) lateinit var toddlers: ToggleButton

    init {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.who_is_travelling, this, true)
        ButterKnife.bind(this)
    }

    private fun getSelected(): List<WhoIsTravelling> {
        var result = mutableListOf<WhoIsTravelling>()
        if (male.isChecked) {
            result.add(WhoIsTravelling.MALE)
        }
        if (fermale.isChecked) {
            result.add(WhoIsTravelling.FERMALE)
        }
        if (babies.isChecked) {
            result.add(WhoIsTravelling.BABIES)
        }
        if (toddlers.isChecked) {
            result.add(WhoIsTravelling.TODDLERS)
        }
        return result.toList()
    }

    private fun setSelected(newValue: List<WhoIsTravelling>) {
        if (newValue.contains(WhoIsTravelling.MALE)) {
            male.isChecked = true
        }
        if (newValue.contains(WhoIsTravelling.FERMALE)) {
            fermale.isChecked = true
        }
        if (newValue.contains(WhoIsTravelling.BABIES)) {
            babies.isChecked = true
        }
        if (newValue.contains(WhoIsTravelling.TODDLERS)) {
            toddlers.isChecked = true
        }
    }

    enum class WhoIsTravelling {
        MALE, FERMALE, BABIES, TODDLERS
    }
}