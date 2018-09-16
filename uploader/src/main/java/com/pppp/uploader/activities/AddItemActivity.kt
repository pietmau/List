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

open class AddItemActivity : AppCompatActivity() {
    val categoriesSelected =
        TreeSet<Category>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    val tagSelected =
        TreeSet<Tag>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    private val db = CloudFirestoreCheckListDatabase()
    private val itemsOnDb: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_items)
        db.getCategories().subscribe({ onCategoriesAvailable(it) }, {})
        db.getTags().subscribe({ onTagsAvailable(it) }, {})
        db.subscribeToItemsAndUpdates().subscribe({ items -> onItemsAvailable(items) }, {})
        button.setOnClickListener { save() }
    }

    private fun onItemsAvailable(items: List<CheckListItem>) {
        itemsOnDb.clear()
        itemsOnDb.addAll(items.map { it.title }.toHashSet())
        itemsrv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        itemsrv.adapter = ItemsAdapter(items.sortedBy { it.title }, ::onItemClicked)
    }

    open fun onItemClicked(checkListItem: CheckListItem) {
        /* NoOp */
    }

    private fun save() {
        val title = name.text?.toString()?.capitalize()
        if (title == null) {
            Toast.makeText(this, "No title", Toast.LENGTH_LONG).show()
            return
        }
        val priorityTxt = getPriority()
        val descriptionTxt = description.text?.toString()?.capitalize()
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
        val optional = check.isChecked
        val item = CheckListItem(
            title,
            false,
            priorityTxt,
            descriptionTxt,
            categoriesSelected.first(),
            tagSelected.toList(),
            optional
        )
        db.saveItem(item).subscribe({}, {})
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
        rendercategories()
    }

    private fun getPriority() = try {
        priorty?.text?.toString()?.toInt() ?: 5
    } catch (exception: NumberFormatException) {
        5
    }

    fun onTagsAvailable(taglist: List<Tag>?) {
        taglist ?: return
        tags.layoutManager = LinearLayoutManager(this)
        tags.adapter = TagsAdapter(taglist.sortedBy { it.title }, ::onTagSelecged)
    }

    fun onCategoriesAvailable(catgs: List<Category>?) {
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

    protected fun renderTags() {
        selected_tags.removeAllViews()
        tagSelected.forEach {
            val tv = getView(it.title)
            selected_tags.addView(tv)
        }
    }

    protected fun rendercategories() {
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