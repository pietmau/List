package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.pppp.database.implementation.CloudFirestoreCheckListDatabase
import com.pppp.entities.pokos.Tag
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_category_activity.*


class AddTagActivity : AppCompatActivity() {
    private val db = CloudFirestoreCheckListDatabase()
    private val itemsOnDb: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_category_activity)
        db.subscribeToTagsAndUpdates().subscribe({ onCategoriesAvailable(it) }, {})
        button.setOnClickListener { save() }
        description.visibility = View.GONE
    }

    private fun save() {
        val title = name.text?.toString()?.capitalize()
        if (title == null) {
            Toast.makeText(this, "Nono", Toast.LENGTH_LONG).show()
            return
        }
        if (itemsOnDb.contains(title)) {
            Toast.makeText(this, "Nono", Toast.LENGTH_LONG).show()
            return
        }
        val tag = Tag(title, false)
        db.saveTag(tag, tag.hashCode().toString()).subscribe({}, {})
        name.text.clear()
        description.text.clear()
    }

    private fun onCategoriesAvailable(catgs: List<Tag>?) {
        catgs ?: return
        itemsOnDb.clear()
        val elements = catgs.map { it.title }.sorted()
        itemsOnDb.addAll(elements)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = TagsAdapter(catgs.sortedBy { it.title }, {})
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AddTagActivity::class.java)
            context.startActivity(intent)
        }
    }
}