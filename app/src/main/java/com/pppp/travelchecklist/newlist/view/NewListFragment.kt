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
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.di.NewListModule
import com.pppp.travelchecklist.newlist.model.Destination
import com.pppp.travelchecklist.newlist.presenter.NewListPresenter
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

private const val DELAY_IN_MILLS = 1000L

class NewListFragment : Fragment(), NewListCallback {
    @Inject
    lateinit var presenter: NewListPresenter
    private val parent
        get() = (activity as? CreateChecklistView)
    private val handler by lazy { Handler() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        appComponent?.with(NewListModule(requireActivity() as NewListActivity))?.inject(this)
        presenter.states.observe(activity as AppCompatActivity, Observer { viewState: NewListPresenter.ViewState ->
            render(viewState)
        })
        presenter.transientStates = ::showTransientState
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.selector_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectorView.callaback = this
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

    override fun onNameChanged(name: String) {
        presenter.onNameChanged(name)
    }

    override fun onDurationDeselected(item: Tag) {
        presenter.onDurationDeselected(item)
    }

    override fun onAccommodationDeselected(item: Tag) {
        presenter.onAccommodationDeselected(item)
    }

    override fun onWeatherDeselected(item: Tag) {
        presenter.onWeatherDeselected(item)
    }

    override fun onPageChanged(position: Int) {
        presenter.onPageChanged()
    }

    override fun navigateBack() = selectorView.goBack()

    private fun render(viewState: NewListPresenter.ViewState) {
        selectorView.enableFinish(viewState.enableFinish)

        if (viewState.showPreogress) {
            progress_bar.visibility = VISIBLE
            selectorView.visibility = GONE
            text.text = context?.getString(R.string.generating_list)
        }

        if (viewState.listId != null) {
            progress_bar.visibility = GONE
            selectorView.visibility = GONE
            text.text = context?.getString(R.string.list_ready)
            handler.postDelayed({
                parent?.navigateToNewList(viewState.listId)
            }, DELAY_IN_MILLS)
        }
    }

    private fun showTransientState(transientState: NewListPresenter.TransientState) {
        transientState.genericError?.let {
            parent?.onError(it.message)
            selectorView.animateBackButton()
        }
        transientState.noName?.let { selectorView.setNameInputError(resources.getString(R.string.please_input_name)) }
    }

    companion object {
        fun newInstance() = NewListFragment()
    }
}