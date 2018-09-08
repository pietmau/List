package com.pppp.uploader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.database.CheckListDatabase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var checkListDatabase: CheckListDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerUploaderComponent.create().inject(this)
        checkListDatabase.getTags().subscribe({

        }, {

        })
    }
}
