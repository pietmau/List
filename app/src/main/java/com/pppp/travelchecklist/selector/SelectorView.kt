package com.pppp.travelchecklist.selector

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ViewFlipper
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.pppp.travelchecklist.R


class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @BindView(R.id.flipper) lateinit var flipper: ViewFlipper
    private val canGoToNext
        get() = flipper.displayedChild < flipper.childCount - 1
    private val canGoToPrevious
        get() = flipper.displayedChild > 0

    init {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.selector_custom_view, this, true)
        ButterKnife.bind(this)
        addSubViews()
    }

    private fun addSubViews() {
        addViewToFlipper(R.layout.one)
        addViewToFlipper(R.layout.two)
    }

    private fun addViewToFlipper(@LayoutRes layout: Int) {
        flipper.addView((context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(layout, null, false))
    }

    private fun addViewToFlipper(view: View) {
        flipper.addView(view)
    }

    @OnClick(R.id.previous)
    fun onPreviousClicked() {
        if (canGoToPrevious) {
            flipper.showPrevious()
        }
    }

    @OnClick(R.id.next)
    fun onNextClicked() {
        if (canGoToNext) {
            flipper.showNext()
        }
    }


}