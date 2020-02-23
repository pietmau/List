package com.pppp.travelchecklist.utils

import android.view.View

class DebouncingClickListener(private val block: (() -> Unit)? = null, private val delayInMills: Long = 1000) : View.OnClickListener {
    private var timeOfPreviousClick: Long? = null

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (timeOfPreviousClick == null) {
            run(now)
            return
        }
        if (timeHasElapsed(now)) {
            run(now)
            return
        }
        timeOfPreviousClick = now
    }

    private fun run(now: Long) {
        timeOfPreviousClick = now
        block?.invoke()
    }

    private fun timeHasElapsed(now: Long) = requireNotNull(timeOfPreviousClick) + delayInMills < now
}

fun View.setDebouncingClickListener(onClick: (() -> Unit)? = null) = setOnClickListener(DebouncingClickListener(onClick))