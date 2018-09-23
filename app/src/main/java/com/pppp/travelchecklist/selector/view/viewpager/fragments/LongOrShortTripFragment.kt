package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.TripLengthMapper
import kotlinx.android.synthetic.main.long_or_short.*
import javax.inject.Inject

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment() {
    @Inject
    lateinit var mapper: TripLengthMapper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.long_or_short, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.long_or_short)
        strip.listener = this
        //strip.setItems(getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = TODO()

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback.onDurationSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }
}