package com.pppp.travelchecklist.createlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.viewmodel.CreateChecklistView
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.createlist.initialdownload.InitialDownloadFragment
import com.pppp.travelchecklist.createlist.view.NewListCallback
import com.pppp.travelchecklist.createlist.view.NewListFragment
import kotlinx.android.synthetic.main.activity_create_cheklist.container
import javax.inject.Inject

class NewListActivity : AppCompatActivity(), CreateChecklistView {
    override val selectionCallback: NewListCallback?
        get() = (supportFragmentManager.findFragmentById(R.id.container) as? NewListCallback)

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cheklist)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, InitialDownloadFragment.newInstance()).commit()
        }
    }

    override fun navigateToNewList(checkListId: String) {
        val data = Intent().apply {
            putExtra(CHECKLIST_ID, checkListId)
        }
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onError(text: String) {
        Snackbar.make(container, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        if (selectionCallback?.navigateBack() == true) {
            return
        }
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
    }

    override fun navigateToSelector() {
        supportFragmentManager.beginTransaction().replace(R.id.container, NewListFragment.newInstance()).commit()
    }

    companion object {
        const val CREATE_NEW_LIST = 123
        const val CHECKLIST_ID = "checklist_id"
    }
}
