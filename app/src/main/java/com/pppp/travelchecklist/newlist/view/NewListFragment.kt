package com.pppp.travelchecklist.newlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.newlist.NewListModule
import com.pppp.travelchecklist.newlist.model.Destination
import com.pppp.travelchecklist.newlist.presenter.NewListPresenter
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

class NewListFragment() : Fragment(), NewListCallback, NewListView {
    @Inject
    lateinit var presenter: NewListPresenter
    private val mainView
        get() = (activity as? MainView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        appComponent?.with(NewListModule(requireActivity()))?.inject(this)
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

    override fun onPlannedActivitySelected(plannedActivity: Tag) {
        presenter.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: Tag) {
        presenter.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Tag) {
        presenter.onWhoisTravellingSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(traveller: Tag) {
        presenter.onWhoisTravellingDeSelected(traveller)
    }

    override fun onDurationSelected(duration: Tag) {
        presenter.onDurationSelected(duration)
    }

    override fun onAccommodationSelected(accomodation: Tag) {
        presenter.onAccommodationSelected(accomodation)
    }

    override fun onWeatherSelected(weather: Tag) {
        presenter.onWeatherSelected(weather)
    }

    override fun onFinishClicked() {
        presenter.onFinishClicked()
    }

    override fun onError(string: String) {
        mainView?.onError(string)
    }

    override fun onDestinationSelected(destination: Destination) {
        presenter.onDestinationSelected(destination)
    }

    override fun onListGenerated(checkListId: String) {
        mainView?.navigateToNewList(checkListId)
    }

    override fun generatingList() {
        selector.visibility = View.GONE
    }

    companion object {
        fun newInstance() = NewListFragment()
        val TAG = NewListFragment::class.java.simpleName
    }
}