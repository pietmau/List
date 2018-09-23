package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.WhoIsTravellingMapper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.who_is_travelling.*
import javax.inject.Inject

class WhoIsTravellingFragment : ItemSelectorFragment() {
    private val container = CompositeDisposable()
    @Inject
    lateinit var mapper: WhoIsTravellingMapper
    @Inject
    lateinit var model: WhoIsTravellingModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.who_is_travelling, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.who_is_travelling)
        strip.callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        container.add(model.getWhoIsTravelling().subscribe({ setItems(it) }, {}))
    }

    override fun onPause() {
        super.onPause()
        container.clear()
    }

    private fun setItems(group: List<Tag>) {
        strip.setItems(group)
    }

    override fun getItems() = throw RuntimeException("Unused")

    override fun onItemSelected(item: ButtonsStrip.Item) {
        val traveller = item.data as? Tag
        traveller ?: return
        model.onWhoisTravellingSelected(traveller)
        callback.onWhoisTravellingSelected(traveller)
    }

    override fun onItemDeSelected(item: ButtonsStrip.Item) {
        callback.onWhoisTravellingDeSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = WhoIsTravellingFragment()
    }
}