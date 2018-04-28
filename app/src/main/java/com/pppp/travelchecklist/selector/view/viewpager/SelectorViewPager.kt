package com.pppp.travelchecklist.selector.view.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import com.pppp.travelchecklist.extensions.isMarshmallowOrAbove
import com.pppp.travelchecklist.selector.view.model.Selection


class SelectorViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    val canGoToNext
        get() = currentItem < (adapter!!.count - 1)
    val canGoToPrevious
        get() = currentItem > 0

    init {
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        adapter = SelectionViewPagerAdapter(fragmentManager)
        offscreenPageLimit = adapter!!.count
        if (isMarshmallowOrAbove) {
            setScrollListener()
        }
    }

    @SuppressLint("NewApi")
    private fun setScrollListener() {
        setOnScrollChangeListener({ p0, p1, p2, p3, p4 -> Log.e("Scroll", "") })
    }

    fun showPrevious() {
        currentItem = currentItem - 1
    }

    fun showNext() {
        currentItem = currentItem + 1
    }

    fun getSelection(): Selection = (adapter as SelectionViewPagerAdapter).getSelection()
}