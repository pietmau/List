package com.pppp.travelchecklist.selector

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.SelectorFragment


class SelectionActivity : AppCompatActivity() {
    val selectorCallback
        get() = (supportFragmentManager.findFragmentById(R.id.selectorfragment) as? SelectorFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_activity)
    }

}