package com.pppp.travelchecklist.selector.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.model.Weather
import com.pppp.travelchecklist.selector.model.Duration
import javax.inject.Inject

class SelectorFragment : Fragment(), SelectorCallback {
    override fun onLengthSelected(duration: Duration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAccomodationSelected(accomodation: Accomodation) {

    }

    override fun onWeatherSelected(weather: Weather) {

    }

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
    ): View? {
        return inflater.inflate(R.layout.selector_fragment, container, false)
    }


    companion object {
        fun newInstance() = SelectorFragment()
        val TAG = SelectorFragment::class.java.simpleName
    }

    override fun onFinishClicked() {

    }

}