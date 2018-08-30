package com.pppp.travelchecklist

import android.os.Bundle
import android.support.v4.app.Fragment
import com.pppp.travelchecklist.selector.presenter.SelectionData


class ListFragment : Fragment() {

    companion object {
        val TAG = ListFragment::class.java.simpleName

        fun fromSelection(selection: SelectionData): ListFragment {
            val frag = ListFragment()
            val bundle = Bundle()
            bundle.putParcelable(SelectionData.TAG, selection)
            frag.arguments = bundle
            return frag
        }
    }
}