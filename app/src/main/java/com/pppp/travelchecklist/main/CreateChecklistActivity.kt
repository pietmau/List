package com.pppp.travelchecklist.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.newlist.view.NewListCallback
import javax.inject.Inject

class CreateChecklistActivity : AppCompatActivity(), MainView {

    override val selectionCallback: NewListCallback?
        get() = (supportFragmentManager.findFragmentById(R.id.selector_fragment) as? NewListCallback)

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cheklist)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun navigateToNewList(checkListId: String) {
        //supportFragmentManager.findFragmentById(R.id.container)?.let { fragmentTransaction.remove(it) }
        //fragmentTransaction.replace(R.id.container, ViewCheckListFragment.fromSelection(checkListId), ViewCheckListFragment.TAG).commit()
    }

    override fun onError(text: String) {
        //selector_fragment.view?.let {
        //    Snackbar.make(it, text, Snackbar.LENGTH_LONG).show()
        // }
    }
}
