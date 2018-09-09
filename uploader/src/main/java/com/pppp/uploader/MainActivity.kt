package com.pppp.uploader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.database.CheckListDatabase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var database: CheckListDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerUploaderComponent.create().inject(this)

    }

    override fun onResume() {
        super.onResume()
        database.start()
        database.getTags().subscribe({}, {})
        database.getItems().subscribe()
        database.getCategories().subscribe({

        }, {})
    }

    override fun onPause() {
        super.onPause()
        database.stop()
    }
}
