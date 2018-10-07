package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.pppp.database.implementation.CloudFirestoreCheckListDatabase
import com.pppp.entities.Category
import com.pppp.entities.Tag
import com.pppp.entities.TagsGroup
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_items.*
import java.util.*

open class AddGroupActivity : AppCompatActivity() {
    val categoriesSelected =
        TreeSet<Category>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    val tagSelected =
        TreeSet<Tag>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    private val db = CloudFirestoreCheckListDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_items)
        db.getTags().subscribe({ onTagsAvailable(it) }, {})
        button.setOnClickListener { save() }
        priorty.visibility = View.GONE
        categories_container.visibility = View.GONE
        selected_categories_container.visibility = View.GONE
    }

    private fun save() {
        val title = name.text?.toString()?.capitalize()
        if (title == null) {
            Toast.makeText(this, "No title", Toast.LENGTH_LONG).show()
            return
        }
        val descriptionTxt = description.text?.toString()?.capitalize()
        if (tagSelected.isEmpty()) {
            Toast.makeText(this, "No Tags", Toast.LENGTH_LONG).show()
            return
        }
        val exclusive = check.isChecked
        val item = TagsGroup(title, descriptionTxt, tagSelected.toList(), exclusive)

        db.saveTagGroup(item, item.key).subscribe({}, {})
        clearAll()
    }

    fun clearAll() {
        name.text.clear()
        priorty.text.clear()
        description.text.clear()
        categoriesSelected.clear()
        tagSelected.clear()
        check.isChecked = false
        renderTags()
    }

    fun onTagsAvailable(taglist: List<Tag>?) {
        taglist ?: return
        tags.layoutManager = LinearLayoutManager(this)
        tags.adapter = TagsAdapter(taglist.sortedBy { it.title }, ::onTagSelecged)
    }

    fun onTagSelecged(tag: Tag) {
        if (tagSelected.contains(tag)) {
            tagSelected.remove(tag)
        } else {
            tagSelected.add(tag)
        }
        renderTags()
    }

    protected fun renderTags() {
        selected_tags.removeAllViews()
        tagSelected.forEach {
            val tv = getView(it.title)
            selected_tags.addView(tv)
        }
    }

    private fun getView(text: String): TextView {
        val inflater = LayoutInflater.from(this)
        val tv = inflater.inflate(R.layout.flex_item, null) as TextView
        tv.text = text
        return tv
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AddGroupActivity::class.java)
            context.startActivity(intent)
        }
    }
}