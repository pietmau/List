package com.pppp.mapper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.mapper.network.Client
import com.pppp.travelchecklist.server.mapping.Mapping
import kotlinx.android.synthetic.main.activity_tags.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TagsActitity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)
        GlobalScope.launch {
            try {
                val tags = Client.getTags().sortedBy { it.title }
                val items = Client.getItems()
                withContext(Dispatchers.Main) {
                    setUpRecycler(tags, items)
                }
            } catch (ignored: Exception) {

            }
        }
        save.setOnClickListener {
            val tags = (recycler.adapter as TagsAdapter).getTags().map { it.id }.filterNotNull()
            GlobalScope.launch {
                val mapping = Mapping(getItemId(), tags)
                Client.mapping(mapping)
            }
        }
    }

    private fun setUpRecycler(tags: List<Tag>, items: List<CheckListItem>) {
        val id = getItemId() ?: return
        val item = items.find { item -> item.id == id } ?: return
        itemTitle.text = intent.getStringExtra("title")
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = TagsAdapter(tags.toMutableList(), item.tags.toMutableList())
    }

    private fun getItemId() = intent.getLongExtra("itemId", -1)
}