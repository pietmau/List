package com.pppp.travelchecklist.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.Model
import com.pppp.travelchecklist.model.SimpleObserver
import kotlinx.android.synthetic.main.fragment_blank.*

class TestFragment : Fragment() {
    private var model: Model? = null

    private val callback = object : CheckListCard.Callback {
        override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int) {

        }

        override fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = Model()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onPause() {
        super.onPause()
        recycler.callback = null
    }

    override fun onResume() {
        super.onResume()
        recycler.callback = callback
        model?.subscribe(object : SimpleObserver<List<CardItemData>>() {
            override fun onNext(itemData: List<CardItemData>) {
                recycler.setItems(itemData)
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
