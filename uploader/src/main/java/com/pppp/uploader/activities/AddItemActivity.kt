package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.pppp.database.CloudFirestoreCheckListDatabase
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_items.*
import java.util.*

class AddItemActivity : AppCompatActivity() {
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
            onItemsAvailable(items)
        }, {})
        button.setOnClickListener { save() }
    }

    private fun onItemsAvailable(items: List<CheckListItem>) {
        itemsOnDb.clear()
        itemsOnDb.addAll(items.map { it.title }.toHashSet())
        itemsrv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        itemsrv.adapter = ItemsAdapter(items.map { it.title }.sorted())
    }

    private fun save() {
        val title = name.text?.toString()
        if (title == null) {
            Toast.makeText(this, "No title", Toast.LENGTH_LONG).show()
            return
        }
        val priorityTxt = getPriority()
        val descriptionTxt = description.text?.toString()
        if (itemsOnDb.contains(title)) {
            Toast.makeText(this, "Already existing", Toast.LENGTH_LONG).show()
            return
        }
        if (tagSelected.isEmpty()) {
            Toast.makeText(this, "No Tags", Toast.LENGTH_LONG).show()
            return
        }
        if (categoriesSelected.isEmpty()) {
            Toast.makeText(this, "No Category", Toast.LENGTH_LONG).show()
            return
        }
        val item = CheckListItem(
            title,
            false,
            priorityTxt,
            descriptionTxt,
            categoriesSelected.first(),
            tagSelected.toList()
        )
        db.saveItem(item).subscribe({}, {})
        clearAll()
    }

    private fun clearAll() {
        name.text.clear()
        priorty.text.clear()
        description.text.clear()
        categoriesSelected.clear()
        tagSelected.clear()
        renderTags()
        rendercategories()
    }

    private fun getPriority() = try {
        priorty?.text?.toString()?.toInt() ?: 5
    } catch (exception: NumberFormatException) {
        5
    }

    private fun onTagsAvailable(taglist: List<Tag>?) {
        taglist ?: return
        tags.layoutManager = LinearLayoutManager(this)
        tags.adapter = TagsAdapter(taglist.sortedBy { it.title }, ::onTagSelecged)
    }

    private fun onCategoriesAvailable(catgs: List<Category>?) {
        catgs ?: return
        categories.layoutManager = LinearLayoutManager(this)
        categories.adapter = CategoryAdapter(catgs.sortedBy { it.title }, ::onCategoryClicked)
    }

    fun onCategoryClicked(category: Category) {
        if (categoriesSelected.contains(category)) {
            categoriesSelected.remove(category)
        } else {
            categoriesSelected.add(category)
        }
        rendercategories()
    }

    fun onTagSelecged(tag: Tag) {
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