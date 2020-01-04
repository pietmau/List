package com.pppp.travelchecklist.createlist.view

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
import com.pppp.travelchecklist.main.viewmodel.CreateChecklistView
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.createlist.di.NewListModule
import com.pppp.travelchecklist.createlist.model.Destination
import com.pppp.travelchecklist.createlist.presenter.NewListViewModel
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

private const val DELAY_IN_MILLS = 1000L

class NewListFragment : Fragment(), NewListCallback {
    @Inject
    lateinit var viewModel: NewListViewModel
    private val parent
        get() = (activity as? CreateChecklistView)
    private val handler by lazy { Handler() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        appComponent?.with(NewListModule(requireActivity() as NewListActivity))?.inject(this)
        viewModel.states.observe(activity as AppCompatActivity, Observer { viewState: NewListViewModel.NewListViewState ->
            render(viewState)
        })
        viewModel.transientStates = ::showTransientState
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
        viewModel.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: Tag) {
        viewModel.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Tag) {
        viewModel.onWhoisTravellingSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(traveller: Tag) {
        viewModel.onWhoisTravellingDeSelected(traveller)
    }

    override fun onDurationSelected(duration: Tag) {
        viewModel.onDurationSelected(duration)
    }

    override fun onAccommodationSelected(accomodation: Tag) {
        viewModel.onAccommodationSelected(accomodation)
    }

    override fun onWeatherSelected(weather: Tag) {
        viewModel.onWeatherSelected(weather)
    }

    override fun onFinishClicked() {
        viewModel.onFinishClicked()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onDestinationSelected(destination: Destination) {
        viewModel.onDestinationSelected(destination)
    }

    override fun onNameChanged(name: String) {
        viewModel.onNameChanged(name)
    }

    override fun onDurationDeselected(item: Tag) {
        viewModel.onDurationDeselected(item)
    }

    override fun onAccommodationDeselected(item: Tag) {
        viewModel.onAccommodationDeselected(item)
    }

    override fun onWeatherDeselected(item: Tag) {
        viewModel.onWeatherDeselected(item)
    }

    override fun onPageChanged(position: Int) {
        viewModel.onPageChanged()
    }

    override fun navigateBack() = selectorView.goBack()

    private fun render(viewState: NewListViewModel.NewListViewState) {
        selectorView.enableFinish(viewState.enableFinish)

        if (viewState.showProgress) {
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

    private fun showTransientState(transientState: NewListViewModel.TransientState) {
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