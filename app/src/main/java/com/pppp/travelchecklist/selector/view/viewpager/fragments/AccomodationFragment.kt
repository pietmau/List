package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.AccomodationMapper
import kotlinx.android.synthetic.main.accomodation.*
import javax.inject.Inject

class AccomodationFragment : ButtonsStripGroupListenerFragment() {
    @Inject
    lateinit var mapper: AccomodationMapper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.accomodation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accomodation.title = resources.getString(R.string.accomodation)
        accomodation.listener = this
        accomodation.setItems(getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = listOf(
        ButtonsStrip.Item(resources.getString(R.string.hotel)),
        ButtonsStrip.Item(resources.getString(R.string.hostel)),
        ButtonsStrip.Item(resources.getString(R.string.camping))
    )

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback.onAccomodationSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = AccomodationFragment()
    }
}