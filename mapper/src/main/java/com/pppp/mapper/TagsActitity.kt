package com.pppp.mapper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.mapper.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TagsActitity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            try {
                val tags = Client.getTags()
                val items = Client.getItems()
                withContext(Dispatchers.Main) {
                    
                }
            } catch (ignored: Exception) {

            }
        }

    }
}