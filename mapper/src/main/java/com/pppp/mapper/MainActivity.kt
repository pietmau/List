package com.pppp.mapper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.mapper.network.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            try {
                val items = Client.getItems()
                withContext(Dispatchers.Main) {
                    setUpAdapter(items, emptyList())
                }
            } catch (ignored: Exception) {
                ggg()
            }
        }

    }

    private fun ggg() {}

    private fun setUpAdapter(
        items: List<CheckListItemImpl>,
        tags: List<TagImpl>
    ) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ItemsTagsAdapter(items, tags) { item, tags ->
            val ggcccg = Intent(this, TagsActitity::class.java)
            ggcccg.putExtra("id", item.id)
            startActivity(ggcccg)
        }
    }
}
