package com.pppp.travelchecklist

import android.support.v4.app.Fragment
import com.pppp.travelchecklist.selector.view.model.Selection


class NewListFragment : Fragment() {

    companion object {
        val TAG = NewListFragment::class.java.simpleName
        fun newInstance(selection: Selection): NewListFragment {
            val frag = NewListFragment()
            //frag.arguments = selection
            return frag
        }
    }
}