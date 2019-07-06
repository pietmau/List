package com.pppp.uploader.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.uploader.R
import kotlinx.android.synthetic.main.add_category_activity.*


class AddCategoryActivity : AppCompatActivity() {
    private val db = CloudFirestoreCheckListDatabase()
    private val itemsOnDb: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_category_activity)
        db.subscribeToCategoriesAndUpdates().subscribe({ onCategoriesAvailable(it) }, {})
        button.setOnClickListener { save() }
    }

    private fun save() {
        val title = name.text?.toString()?.capitalize()
        if (title == null) {
            Toast.makeText(this, "Nono", Toast.LENGTH_LONG).show()
            return
        }
        val descriptionTxt = description.text?.toString()?.capitalize()
        if (itemsOnDb.contains(title)) {
            Toast.makeText(this, "Nono", Toast.LENGTH_LONG).show()
            return
        }
        val category = CategoryImpl(title, descriptionTxt)
        db.saveCategory(category, category.hashCode().toString()).subscribe({}, {})
        name.text.clear()
        description.text.clear()
    }

    private fun onCategoriesAvailable(catgs: List<CategoryImpl>?) {
        catgs ?: return
        itemsOnDb.clear()
        val elements = catgs.map { it.title }.sorted()
        itemsOnDb.addAll(elements)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = CategoryAdapter(catgs.sortedBy { it.title }, {})
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AddCategoryActivity::class.java)
            context.startActivity(intent)
        }
    }
}