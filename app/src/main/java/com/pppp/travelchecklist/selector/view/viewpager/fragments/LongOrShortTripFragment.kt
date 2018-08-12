package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.SelectorCallback
import kotlinx.android.synthetic.main.long_or_short.*

class LongOrShortTripFragment : Fragment() {
    private val callback
        get() = activity as? SelectorCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.long_or_short, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yes.setOnCheckedChangeListener { button, isChecked ->
            no.isChecked = false
            yes.isChecked = isChecked
        }
        no.setOnCheckedChangeListener { button, isChecked ->
            yes.isChecked = false
            no.isChecked = isChecked
        }
    }

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }
}