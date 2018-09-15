package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.pppp.database.CloudFirestoreCheckListDatabase
import com.pppp.entities.Category
import com.pppp.entities.Tag
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_items.*
import java.util.*

class AddItemActivity : AppCompatActivity(), CategoryAdapter.Listener, TagsAdapter.Listener {
    private val categoriesSelected =
        TreeSet<Category>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    private val tagSelected =
        TreeSet<Tag>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    private val db = CloudFirestoreCheckListDatabase()
    private val itemsOnDb: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_items)
        db.getCategories().subscribe({ onCategoriesAvailable(it) }, {})
        db.getTags().subscribe({ onTagsAvailable(it) }, {})
        db.subscribeToItemsAndUpdates().subscribe({ items ->
            itemsOnDb.clear()
            itemsOnDb.addAll(items.map { it.title }.toHashSet())
        }, {})
        button.setOnClickListener { save() }
    }

    private fun save() {
        val name = name.text?.toString()
        val priorty = getPriority()
        val description = description.text?.toString()
        if (itemsOnDb.contains(name)) {
            Toast.makeText(this, "Nono", Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun getPriority() =
        try {
            priorty?.text?.toString()?.toInt() ?: 5
        } catch (exception: NumberFormatException) {
            5
        }

    private fun onTagsAvailable(taglist: List<Tag>?) {
        taglist ?: return
        tags.layoutManager = LinearLayoutManager(this)
        tags.adapter = TagsAdapter(taglist.sortedBy { it.title }, this)
    }

    private fun onCategoriesAvailable(catgs: List<Category>?) {
        catgs ?: return
        categories.layoutManager = LinearLayoutManager(this)
        categories.adapter = CategoryAdapter(catgs.sortedBy { it.title }, this)
    }

    override fun onCategoryClicked(category: Category) {
        if (categoriesSelected.contains(category)) {
            categoriesSelected.remove(category)
        } else {
            categoriesSelected.add(category)
        }
        rendercategories()
    }

    override fun onTagSelecged(tag: Tag) {
        if (tagSelected.contains(tag)) {
            tagSelected.remove(tag)
        } else {
            tagSelected.add(tag)
        }
        renderTags()
    }

    private fun renderTags() {
        selected_tags.removeAllViews()
        tagSelected.forEach {
            val tv = getView(it.title)
            selected_tags.addView(tv)
        }
    }

    private fun rendercategories() {
        selected_categories.removeAllViews()
        categoriesSelected.forEach {
            val tv = getView(it.title)
            selected_categories.addView(tv)
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
            val intent = Intent(context, AddItemActivity::class.java)
            context.startActivity(intent)
        }
    }
}