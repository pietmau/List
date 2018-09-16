package com.pppp.uploader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.uploader.activities.AddCategoryActivity
import com.pppp.uploader.activities.AddItemActivity
import com.pppp.uploader.activities.AddTagActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerUploaderComponent.create().inject(this)
        edit_category.setOnClickListener { AddCategoryActivity.start(this) }
        edit_items.setOnClickListener { AddItemActivity.start(this) }
        edit_tags.setOnClickListener { AddTagActivity.start(this) }
    }
}
