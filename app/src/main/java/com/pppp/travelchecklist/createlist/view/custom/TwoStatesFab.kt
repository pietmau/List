package com.pppp.travelchecklist.createlist.view.custom

import android.content.Context
import android.content.res.ColorStateList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.AttributeSet
import com.pppp.travelchecklist.R

class TwoStatesFab @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FloatingActionButton(context, attrs) {

    init {
        setState(State.GO_FORWARD)
    }

    fun setState(state: State) = when (state) {
        State.GO_FORWARD -> {
            backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green_super_light)) // TODO use custom attrs!
            setImageDrawable(resources.getDrawable(R.drawable.ic_chevron_right_white_24dp, context.theme))
        }
        State.CANNOT_FINISH -> {
            backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_close_24px, context.theme))
        }
        State.CAN_FINISH -> {
            backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green_super_light))
            setImageDrawable(resources.getDrawable(R.drawable.ic_check_white_24dp, context.theme))
        }
    }

    enum class State {
        GO_FORWARD, CAN_FINISH, CANNOT_FINISH
    }
}