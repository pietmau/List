package com.pppp.travelchecklist

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

inline fun <reified T : Fragment> FragmentActivity.findFragmentByTag(tag: String): T? {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    if (fragment is T) {
        return fragment
    }
    return null
}


inline fun <reified T : Fragment> FragmentActivity.findFragmentById(id: Int): T? {
    val fragment = supportFragmentManager.findFragmentById(id)
    if (fragment is T) {
        return fragment
    }
    return null
}