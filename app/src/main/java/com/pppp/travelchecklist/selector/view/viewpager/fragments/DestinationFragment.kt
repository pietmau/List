package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import butterknife.BindView
import butterknife.ButterKnife
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.database.Country
import com.pppp.travelchecklist.database.DestinationPresenter
import com.pppp.travelchecklist.model.SimpleObserver
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.SelectorCallback
import org.angmarch.views.BetterSpinner
import javax.inject.Inject


class DestinationFragment : Fragment() {
    protected val callback
        get() = activity as? SelectorCallback

    @Inject
    lateinit var presenter: DestinationPresenter
    @BindView(R.id.nice_spinner)
    lateinit var spinner: BetterSpinner

    companion object {
        fun newInstance() = DestinationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.where_are_you_flying, container, false)
        ButterKnife.bind(this, view)
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        })
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        val selectorComponent = appComponent?.with(SelectorModule(activity as AppCompatActivity))
        selectorComponent?.inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getCountries(object : SimpleObserver<List<Country>>() {
            override fun onNext(countries: List<Country>) {
                onCountriesAvailable(countries)
            }
        })
    }

    private fun onCountriesAvailable(countries: List<Country>) {
        spinner.attachDataSource(countries)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

}