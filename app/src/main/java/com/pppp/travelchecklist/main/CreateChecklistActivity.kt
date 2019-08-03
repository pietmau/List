package com.pppp.travelchecklist.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.CreateChecklistView
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.newlist.view.NewListCallback
import kotlinx.android.synthetic.main.activity_create_cheklist.selector_fragment
import javax.inject.Inject

class CreateChecklistActivity : AppCompatActivity(), CreateChecklistView {

    override val selectionCallback: NewListCallback?
        get() = (supportFragmentManager.findFragmentById(R.id.selector_fragment) as? NewListCallback)

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cheklist)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
    }

    override fun navigateToNewList(checkListId: String) {
        //supportFragmentManager.findFragmentById(R.id.container)?.let { fragmentTransaction.remove(it) }
        //fragmentTransaction.replace(R.id.container, ViewCheckListFragment.fromSelection(checkListId), ViewCheckListFragment.TAG).commit()
    }

    override fun onError(text: String) {
        selector_fragment.view?.let {
            Snackbar.make(it, text, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
    }

    companion object {
        const val CODE: Int = 123
    }
}
