package com.pppp.travelchecklist.selector.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.MainActivity
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.model.*
import com.pppp.travelchecklist.selector.presenter.SelectionData
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

class SelectorFragment : Fragment(), SelectorCallback, ISelectorView {
    @Inject
    lateinit var presenter: SelectorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        appComponent?.with(SelectorModule(requireActivity()))?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.selector_fragment, container, false)

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selector.callaback = this
    }

    override fun onPlannedActivitySelected(plannedActivity: PlannedActivity) {
        presenter.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: PlannedActivity) {
        presenter.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Traveller) {
        presenter.onWhoisTravellingSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(traveller: Traveller) {
        presenter.onWhoisTravellingDeSelected(traveller)
    }

    override fun onDurationSelected(duration: Duration) {
        presenter.onDurationSelected(duration)
    }

    override fun onAccomodationSelected(accomodation: Accomodation) {
        presenter.onAccomodationSelected(accomodation)
    }

    override fun onWeatherSelected(weather: Weather) {
        presenter.onWeatherSelected(weather)
    }

    override fun onFinishClicked() {
        presenter.onFinishClicked()
    }

    override fun onError(string: String?) {

    }

    override fun generateAndViewList(selection: SelectionData) {
        (activity as? MainActivity)?.navigateToNewList(selection)
    }

    override fun onDestinationSelected(destination: Destination) {
        presenter.onDestinationSelected(destination)
    }

    companion object {
        fun newInstance() = SelectorFragment()
        val TAG = SelectorFragment::class.java.simpleName
    }
}