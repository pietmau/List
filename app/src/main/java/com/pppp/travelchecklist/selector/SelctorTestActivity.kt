package com.pppp.travelchecklist.selector

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import kotlinx.android.synthetic.main.strip_example.*

class SelctorTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.strip_example)
        strip.title = "Title"
        strip.setItems(getItems())
    }

    private fun getItems(): List<ButtonsStrip.Item> {
        val list = mutableListOf<ButtonsStrip.Item>()
        list.add(ButtonsStrip.Item("Foo"))
        list.add(ButtonsStrip.Item("Bar"))
        list.add(ButtonsStrip.Item("FooBar"))
        return list
    }
}