package com.pppp.travelchecklist.selector.view.viewpager.fragments

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.utils.ResourcesWrapper

class LongOrShortTripFragmentPresenter(private val resources: ResourcesWrapper) {
    private var view: LongOrShortTripView? = null

    fun onItemSelected(item: ButtonsStrip.Item) {
        when {
            item.description.equals(resources.getString(R.string.yes), true)
            -> view?.onLengthSelected(SelectorCallback.Duration.LONG)

            item.description.equals(resources.getString(R.string.no), true)
            -> view?.onLengthSelected(SelectorCallback.Duration.SHORT)
        }
    }

    fun bind(longOrShortTripView: LongOrShortTripView) {
        this.view = longOrShortTripView
    }
}
