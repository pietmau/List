package com.pppp.travelchecklist.newlist.view.viewpager

import android.annotation.SuppressLint
import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.pppp.travelchecklist.extensions.isMarshmallowOrAbove
import com.pppp.travelchecklist.newlist.view.viewpager.fragments.GetNameFragment

class SelectorViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ViewPager(context, attrs) {

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

    fun setNameInputError(nameInputError: String?) {
        val fragment = adapter?.instantiateItem(this, 6)
        (fragment as? GetNameFragment)?.setNameInputError(nameInputError)
    }
}