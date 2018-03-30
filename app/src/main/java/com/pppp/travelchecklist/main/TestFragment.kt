package com.pppp.travelchecklist.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.card.carditem.CustomAlertDialogBuilder
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.SimpleObserver
import kotlinx.android.synthetic.main.fragment_blank.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class TestFragment : Fragment(), TravelListView {
    @Inject lateinit var presenter: MainPresenter

    private val callback = object : CheckListCard.Callback {
        override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int, data: CheckListItemData) {
            this@TestFragment.onItemDeleteRequested(cardPosition, itemPosition, data)
        }

        override fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int) {
            this@TestFragment.onItemSettingsRequested(cardPosition, itemPosition)
        }
    }

    private fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int) {
        context?.let { CustomAlertDialogBuilder(it, presenter.getItem(cardPosition, itemPosition)).create().show() }
    }

    private fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int, data: CheckListItemData) {
        activity?.alert(Appcompat,
                resources.getString(R.string.delete) + " " + data.title + "?",
                resources.getString(R.string.delete)) {
            yesButton { deleteChecklistItem(cardPosition, itemPosition) }
            noButton { }
        }?.show()
    }

    private fun deleteChecklistItem(cardPosition: Int, itemPosition: Int) {
        presenter.deleteChecklistItem(cardPosition, itemPosition)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as? App)?.appComponent?.with(MainModule(activity))?.inject(this@TestFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onPause() {
        super.onPause()
        recycler.callback = null
        presenter.unsubscribe()
    }

    override fun onResume() {
        super.onResume()
        recycler.callback = callback
        presenter.subscribe(this, object : SimpleObserver<List<CardItemData>>() {
            override fun onNext(items: List<CardItemData>) {
                recycler.setItems(items)
            }
        })
    }

    companion object {
        val TAG = TestFragment::class.simpleName
        fun newInstance(): TestFragment {
            val fragment = TestFragment()
            return fragment
        }
    }

}
