package com.pppp.travelchecklist.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.SimpleObserver
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_blank.*
import javax.inject.Inject

class TestFragment : Fragment(), TravelListView {
    @Inject lateinit var presenter: MainPresenter

    val actions = PublishSubject.create<TravelListView.Action>()

    override fun render(viewConfiguration: TravelListView.ViewConfiguration) {
        recycler.setItems(viewConfiguration.items)
    }

    private val callback = object : CheckListCard.Callback {
        override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int) {

        }

        override fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = this@TestFragment.activity as AppCompatActivity
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
        presenter.subscribe(object : SimpleObserver<TravelListView.ViewConfiguration>() {
            override fun onNext(viewConfiguration: TravelListView.ViewConfiguration) {
                render(viewConfiguration)
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
