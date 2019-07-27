package com.pppp.travelchecklist.newlist.view.viewpager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
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
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.utils.SimpleObserver
import com.pppp.travelchecklist.newlist.NewListModule
import com.pppp.travelchecklist.newlist.model.Destination
import org.angmarch.views.BetterSpinner
import javax.inject.Inject


class DestinationFragment : Fragment() {
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback

    @Inject
    lateinit var presenter: DestinationPresenter
    @BindView(R.id.nice_spinner)
    lateinit var spinner: BetterSpinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.where_are_you_flying, container, false)
        ButterKnife.bind(this, view)
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val country = (adapterView?.adapter?.getItem(position) as? Country) ?: return
                callback?.onDestinationSelected(Destination(country.country))
            }
        })
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        val selectorComponent = appComponent?.with(NewListModule(activity as AppCompatActivity))
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