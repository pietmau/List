package com.pppp.travelchecklist.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
    }
}