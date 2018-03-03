package com.pppp.travelchecklist.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import kotlinx.android.synthetic.main.test_layout.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
        val foo = CheckListItemData("Foo", true, Priority(0))
        val bar = CheckListItemData("Bar", false, Priority(10))
        val fobar = CheckListItemData("Fobar", false, Priority(5))
        var list = mutableListOf(foo, bar, fobar)
        for (i in 0..30){
            val itme = CheckListItemData("ggggggg $i", false, Priority(5))
            list.add(itme)
        }
        test_card.setItems(list)
    }
}