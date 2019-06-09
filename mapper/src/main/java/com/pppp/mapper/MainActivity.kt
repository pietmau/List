package com.pppp.mapper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pietrantuono.entities.CheckListItem
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

            }
        }

    }

    private fun setUpAdapter(
        items: List<CheckListItem>,
        tags: List<TagImpl>
    ) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ItemsTagsAdapter(items.sortedBy { it.title }, tags) { item, tags ->
            val ggcccg = Intent(this, TagsActitity::class.java)
            ggcccg.putExtra("itemId", item.id)
            ggcccg.putExtra("title", item.title)
            startActivity(ggcccg)
        }
    }
}
