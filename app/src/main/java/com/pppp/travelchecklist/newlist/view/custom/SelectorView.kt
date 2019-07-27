package com.pppp.travelchecklist.newlist.view.custom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.newlist.NewListModule
import com.pppp.travelchecklist.newlist.view.NewListCallback
import com.pppp.travelchecklist.newlist.view.viewpager.SelectorViewPager

class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    var callaback: NewListCallback? = null
    @BindView(R.id.flipper)
    lateinit var flipper: SelectorViewPager
    @BindView(R.id.next)
    lateinit var next: TwoStatesFab
    @BindView(R.id.previous)
    lateinit var previous: View
    val canGoNext get() = flipper.canGoToNext
    private var time = 0L

    init {
        if (context !is AppCompatActivity) throw UnsupportedOperationException("Must be used within an AppCompatActivity")
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.selector_custom_view, this, true)
        val appComponent = (context.applicationContext as? App)?.appComponent
        val selectorComponent = appComponent?.with(NewListModule(context))
        selectorComponent?.inject(this)
        ButterKnife.bind(this)
        setUp()
    }

    private fun setUp() {//TODO still needed?
        flipper.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                onPageChanged(position)
            }
        })
        onPageChanged(0)
    }

    @OnClick(R.id.previous)
    fun onPreviousClicked() {
        if (clickedTooSoon()) {
            return
        }
        flipper.showPrevious()
    }

    private fun onPageChanged(position: Int) {
        previous.visibility = if (flipper.canGoToPrevious) VISIBLE else GONE
        setUpNextButton()
    }

    private fun setUpNextButton() {
        next.canGoNext = canGoNext
    }

    @OnClick(R.id.next)
    fun onNextClicked() {
        if (clickedTooSoon()) {
            return
        }
        if (canGoNext) {
            flipper.showNext()
        } else {
            callaback?.onFinishClicked()
        }
    }

    private fun clickedTooSoon(): Boolean {
        if (System.currentTimeMillis() - time < THRESHOLD) {
            return true
        } else {
            time = System.currentTimeMillis()
            return false
        }
    }

    companion object {
        private const val THRESHOLD = 300L
    }
}