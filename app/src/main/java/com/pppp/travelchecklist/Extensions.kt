package com.pppp.travelchecklist

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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

val AppCompatActivity.fragmentTransaction: FragmentTransaction
    get() = supportFragmentManager.beginTransaction()

fun View.getChildren(): List<View> {
    if (this !is ViewGroup) {
        return emptyList()
    }
    return (0..this.childCount).map { this.getChildAt(it) }.filterNotNull()
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
