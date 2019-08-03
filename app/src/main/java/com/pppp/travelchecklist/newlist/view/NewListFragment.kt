package com.pppp.travelchecklist.newlist.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.CreateChecklistView
import com.pppp.travelchecklist.newlist.NewListModule
import com.pppp.travelchecklist.newlist.model.Destination
import com.pppp.travelchecklist.newlist.presenter.NewListPresenter
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

class NewListFragment : Fragment(), NewListCallback {
    private val DELAY_IN_MILLS = 1000L
    @Inject
    lateinit var presenter: NewListPresenter
    private val createChecklistView
        get() = (activity as? CreateChecklistView)
    private val handler by lazy { Handler() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        appComponent?.with(NewListModule(requireActivity()))?.inject(this)
        presenter.viewStates.observe(activity as AppCompatActivity, Observer { viewState: NewListPresenter.ViewState ->
            render(viewState)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.selector_fragment, container, false)

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
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

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onDestinationSelected(destination: Destination) {
        presenter.onDestinationSelected(destination)
    }

    override fun generateChecklist(name: String) {
        presenter.setChecklistName(this.text.toString())
    }

    fun showError(incompleteDataMessage: String?, noNameMessage: String?) {
        incompleteDataMessage?.let { createChecklistView?.onError(it) }
        selector.setNameInputError(noNameMessage)
    }

    private fun renderFinish(viewState: NewListPresenter.ViewState.ListGenerated) {
        progress_bar.visibility = GONE
        selector.visibility = GONE
        text.text = context?.getString(R.string.list_ready)
        handler.postDelayed({
            createChecklistView?.navigateToNewList(viewState.listId)
        }, DELAY_IN_MILLS)
    }

    fun renderProgress() {
        progress_bar.visibility = VISIBLE
        selector.visibility = GONE
        text.text = context?.getString(R.string.generating_list)
    }

    private fun render(viewState: NewListPresenter.ViewState) =
        when (viewState) {
            is NewListPresenter.ViewState.Error -> showError(viewState.incompleteDataMessage, viewState.noNameMessage)
            is NewListPresenter.ViewState.Progress -> renderProgress()
            is NewListPresenter.ViewState.ListGenerated -> renderFinish(viewState)
        }
}