package com.pppp.travelchecklist.main.view

import android.content.Context
import android.media.AudioRecord.MetricsConstants.SOURCE
import android.util.AttributeSet
import android.view.View
import android.widget.ViewAnimator
import androidx.annotation.IntDef

class LoadingContentErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewAnimator(context, attrs) {

    fun loading() {
        displayedChild = 0
    }

    fun empty() {
        displayedChild = 1
    }

    fun content() {
        displayedChild = 2
    }

    fun hide() {
        visibility = View.GONE
    }
}