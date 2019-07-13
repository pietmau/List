package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_items.*
import java.util.*

open class AddItemActivity : AppCompatActivity() {
    val categoriesSelected =
        TreeSet<CategoryImpl>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
    val tagSelected =
        TreeSet<TagImpl>(Comparator { o1, o2 -> o1.title.compareTo(o2.title) })
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

    private fun onItemsAvailable(items: List<CheckListItemImpl>) {
        itemsOnDb.clear()
        itemsOnDb.addAll(items.map { it.title }.toHashSet())
        itemsrv.layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(1, androidx.recyclerview.widget.StaggeredGridLayoutManager.HORIZONTAL)
        itemsrv.adapter = ItemsAdapter(items.sortedBy { it.title }, ::onItemClicked)
    }

    open fun onItemClicked(checkListItem: CheckListItemImpl) {
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
            Toast.makeText(this, "No CategoryImpl", Toast.LENGTH_LONG).show()
            return
        }
        val optional = check.isChecked
        val item = CheckListItemImpl(
            title,
            false,
            priorityTxt,
            descriptionTxt,
            categoriesSelected.first(),
            tagSelected.toList(),
            optional
        )
        db.saveItem(item, item.hashCode().toString()).subscribe({}, {})
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

    fun onTagsAvailable(taglist: List<TagImpl>?) {
        taglist ?: return
        tags.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        tags.adapter = TagsAdapter(taglist.sortedBy { it.title }, ::onTagSelecged)
    }

    fun onCategoriesAvailable(catgs: List<CategoryImpl>?) {
        catgs ?: return
        categories.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        categories.adapter = CategoryAdapter(catgs.sortedBy { it.title }, ::onCategoryClicked)
    }

    fun onCategoryClicked(category: CategoryImpl) {
        if (categoriesSelected.contains(category)) {
            categoriesSelected.remove(category)
        } else {
            categoriesSelected.add(category)
        }
        rendercategories()
    }

    fun onTagSelecged(tag: TagImpl) {
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