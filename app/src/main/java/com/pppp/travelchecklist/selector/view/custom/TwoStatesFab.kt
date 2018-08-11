package com.pppp.travelchecklist.selector.view.custom

import android.content.Context
import android.content.res.ColorStateList
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import com.pppp.travelchecklist.R

class TwoStatesFab @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FloatingActionButton(context, attrs) {

    var canGoNext: Boolean = false
        set(value) {
            if (value) {
                backgroundTintList =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                setImageDrawable(
                    resources.getDrawable(R.drawable.ic_chevron_right_white_24dp, context.theme)
                )
            } else {
                backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green))
                setImageDrawable(
                    resources.getDrawable(R.drawable.ic_check_white_24dp, context.theme)
                )
            }
        }
}