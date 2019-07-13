package com.pppp.uploader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.uploader.activities.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerUploaderComponent.create().inject(this)
        add_category.setOnClickListener { AddCategoryActivity.start(this) }
        add_items.setOnClickListener { AddItemActivity.start(this) }
        edit_items.setOnClickListener { EditItemActivity.start(this) }
        add_tags.setOnClickListener { AddTagActivity.start(this) }
        edit_groups.setOnClickListener { AddGroupActivity.start(this) }
    }
}
