package com.pppp.travelchecklist.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.R


class MyFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)
        var frag = supportFragmentManager.findFragmentByTag(TestFragment.TAG)
        if (frag == null) {
            frag = TestFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.frame, frag, TestFragment.TAG).commit()
        }
    }
}