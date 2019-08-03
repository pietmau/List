package com.pppp.travelchecklist.newlist

import android.content.Intent
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
        val data = Intent().apply {
            putExtra(CHECKLIST_ID, checkListId)
        }
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onError(text: String) {
        selector_fragment.view?.let {
            Snackbar.make(it, text, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        if (selectionCallback?.goBack() == true) {
            return
        }
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
    }

    companion object {
        const val REQUEST_CODE = 123
        const val CHECKLIST_ID = "checklist_id"
    }
}
