package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.model.Accomodation
import kotlinx.android.synthetic.main.accomodation.*

class AccomodationFragment : ItemSelectorFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.accomodation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.accomodation)
        strip.callback = this
        strip.setItems(getItems())
    }

    override fun getItems(): List<ButtonsStrip.Item> {
        return listOf(
            ButtonsStrip.Item(resources.getString(R.string.hotel)),
            ButtonsStrip.Item(resources.getString(R.string.hostel)),
            ButtonsStrip.Item(resources.getString(R.string.camping))
        )
    }

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback?.onAccomadationSelected(Accomodation(item))
    }

    companion object {
        fun newInstance() = AccomodationFragment()
    }
}