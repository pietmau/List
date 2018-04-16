package com.pppp.travelchecklist.selector.view.viewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R

class CustomPagerAdapter(private val context: Context) : PagerAdapter() {
    private val layoutInflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)


    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val layout = getlayout(position)
        val view = layoutInflater.inflate(layout, collection, false) as ViewGroup
        collection.addView(view)
        return view
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount() = 4

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int): CharSequence? = ""


    private fun getlayout(position: Int): Int {
        var layout = -1
        when (position) {
            0 -> {
                layout = R.layout.who_is_travelling
            }
            1 -> {
                layout = R.layout.two
            }
            2 -> {
                layout = R.layout.three
            }
            3 -> {
                layout = R.layout.four
            }
            else -> {
                throw UnsupportedOperationException("Invalid position")
            }
        }
        return layout
    }


}