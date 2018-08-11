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
import kotlinx.android.synthetic.main.selector_fragment.*
import javax.inject.Inject

class SelectorFragment : Fragment(), SelectorCallback {
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

    override fun onResume() {
        super.onResume()
        selector.callaback = presenter
    }

    override fun onPause() {
        super.onPause()
        selector.callaback = null
    }

    companion object {
        fun newInstance() = SelectorFragment()
        val TAG = SelectorFragment::class.java.simpleName
    }

    override fun onFinishClicked() {

    }

}