package com.pppp.travelchecklist.selector.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.viewpager.SelectorViewPager

class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @BindView(R.id.flipper) lateinit var flipper: SelectorViewPager
    @BindView(R.id.next) lateinit var next: View
    @BindView(R.id.previous) lateinit var previous: View

    init {
        if (context !is AppCompatActivity) throw UnsupportedOperationException("Must be used within an AppCompatActivity")
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.selector_custom_view, this, true)
        (context.applicationContext as? App)?.appComponent?.with(SelectorModule(context as AppCompatActivity))?.inject(this)
        ButterKnife.bind(this)
        setUp()
    }

    private fun setUp() {
        flipper.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                onPageChanged()
            }
        })
        onPageChanged()
    }

    @OnClick(R.id.previous)
    fun onPreviousClicked() {
        flipper.showPrevious()
    }

    private fun onPageChanged() {
        next.visibility = if (flipper.canGoToNext) VISIBLE else GONE
        previous.visibility = if (flipper.canGoToPrevious) VISIBLE else GONE
    }

    @OnClick(R.id.next)
    fun onNextClicked() {
        flipper.showNext()
    }
}