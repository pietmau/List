package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import kotlinx.android.synthetic.main.long_or_short.*
import javax.inject.Inject

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment(),  LongOrShortTripView {
    @Inject
    lateinit var presenter: LongOrShortTripFragmentPresenter

    private val callback
        get() = activity as? SelectorCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.long_or_short, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.long_or_short)
        strip.listener = this
        strip.setItems(getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity?.applicationContext as? App)?.appComponent
        val selectorComponent = appComponent?.with(SelectorModule(requireActivity()))
        selectorComponent?.inject(this)
        presenter.bind(this)
    }

     override fun getItems(): List<ButtonsStrip.Item> {
        return listOf(
            ButtonsStrip.Item(resources.getString(R.string.yes)),
            ButtonsStrip.Item(resources.getString(R.string.no))
        )
    }
    override fun onItemSelected(item: ButtonsStrip.Item) {
        presenter.onItemSelected(item)
    }

    override fun onLengthSelected(duration: SelectorCallback.Duration) {
        callback?.onLengthSelected(duration)
    }

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }
}