package com.pppp.travelchecklist.selector.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.selector_fragment.*

class SelectorFragment : Fragment() {

    companion object {
        fun newInstance() = SelectorFragment()
        val TAG = SelectorFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.selector_fragment, container, false)
    }

    fun setCallback(callback: SelectorView.Callback?) {
        selector?.callaback = callback
    }
}