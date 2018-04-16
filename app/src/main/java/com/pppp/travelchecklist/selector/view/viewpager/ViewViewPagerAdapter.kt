package com.pppp.travelchecklist.selector.view.viewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R

class ViewViewPagerAdapter(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(getlayout(position), collection, false) as ViewGroup
        collection.addView(view)
        return view
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount() = 4

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int): CharSequence? = TODO("Not implemented")

    private fun getlayout(position: Int) =
        when (position) {
            0 -> R.layout.who_is_travelling
            1 -> R.layout.two
            2 -> R.layout.three
            3 -> R.layout.four
            else -> throw UnsupportedOperationException("Invalid position")
        }

}