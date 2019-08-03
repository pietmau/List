package com.pppp.travelchecklist.newlist.view.custom

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.findViewByIdLazy
import com.pppp.travelchecklist.newlist.NewListModule
import com.pppp.travelchecklist.newlist.view.NewListCallback
import com.pppp.travelchecklist.newlist.view.viewpager.SelectorViewPager

class SelectorView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    var callaback: NewListCallback? = null
    private val flipper: SelectorViewPager by findViewByIdLazy(R.id.flipper)
    private val next: TwoStatesFab by findViewByIdLazy(R.id.next)
    private val previous: View by findViewByIdLazy(R.id.previous)
    private val canGoNext get() = flipper.canGoToNext
    private var time = 0L

    init {
        if (context !is AppCompatActivity) throw UnsupportedOperationException("Must be used within an AppCompatActivity")
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.selector_custom_view, this, true)
        val appComponent = (context.applicationContext as? App)?.appComponent
        val selectorComponent = appComponent?.with(NewListModule(context))
        selectorComponent?.inject(this)
        setUp()
    }

    private fun setUp() {//TODO still needed?
        flipper.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                onPageChanged(position)
            }
        })
        onPageChanged(0)
        previous.setOnClickListener {
            if (!clickedTooSoon()) {
                flipper.showPrevious()
            }
        }
        next.setOnClickListener {
            if (clickedTooSoon()) {
                return@setOnClickListener
            }
            if (canGoNext) {
                flipper.showNext()
            } else {
                callaback?.onFinishClicked()
            }
        }
    }

    private fun onPageChanged(position: Int) {
        previous.visibility = if (flipper.canGoToPrevious) VISIBLE else GONE
        setUpNextButton()
    }

    private fun setUpNextButton() {
        next.canGoNext = canGoNext
    }

    private fun clickedTooSoon(): Boolean {
        if (System.currentTimeMillis() - time < THRESHOLD) {
            return true
        } else {
            time = System.currentTimeMillis()
            return false
        }
    }

    fun setNameInputError(nameInputError: String?) {
        flipper.setNameInputError(nameInputError)
    }

    fun animateBackButton() {
        val anim = android.view.animation.AnimationUtils.loadAnimation(previous.getContext(), R.anim.shake)
        previous.startAnimation(anim)
    }

    companion object {
        private const val THRESHOLD = 300L
    }
}