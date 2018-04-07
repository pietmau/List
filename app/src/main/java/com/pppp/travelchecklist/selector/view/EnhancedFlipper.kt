package com.pppp.travelchecklist.selector.view

import android.content.Context
import android.support.annotation.AnimRes
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import com.pppp.travelchecklist.R


class EnhancedFlipper(context: Context, attributeSet: AttributeSet) : ViewFlipper(context, attributeSet) {
    private val canGoToNext
        get() = displayedChild < childCount - 1
    private val canGoToPrevious
        get() = displayedChild > 0

    override fun showPrevious() {
        if (canGoToPrevious) {
            setInOutAnimations(getAnimation(R.anim.slide_in_left), getAnimation(R.anim.slide_out_right))
            super.showPrevious()
        }
    }

    override fun showNext() {
        if (canGoToNext) {
            setInOutAnimations(getAnimation(R.anim.slide_in_right), getAnimation(R.anim.slide_out_left))
            super.showNext()
        }
    }

    private fun setInOutAnimations(animationIn: Animation?, animationOut: Animation?) {
        setInAnimation(animationIn)
        setOutAnimation(animationOut)
    }

    private fun getAnimation(@AnimRes animation: Int) = AnimationUtils.loadAnimation(context, animation)

}