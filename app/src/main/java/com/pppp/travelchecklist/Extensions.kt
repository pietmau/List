package com.pppp.travelchecklist

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.application.di.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

inline fun <T : View> View.findViewByIdLazy(@IdRes id: Int): Lazy<T> = lazy {
    findViewById<T>(id)
}

inline fun <T : View> Fragment.findViewByIdLazy(@IdRes id: Int): Lazy<T>? = view?.findViewByIdLazy<T>(id)

inline fun <reified T : Fragment> FragmentActivity.findFragmentByTag(tag: String): T? {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
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

val Activity.appComponent: AppComponent?
    get() = (this.application as App).appComponent

val Fragment.appComponent: AppComponent?
    get() = activity?.appComponent

fun EditText.setOnReturnClicked(callback: (TextView.() -> Unit)?) {
    setOnEditorActionListener { textview, action, _ ->
        if (action == EditorInfo.IME_ACTION_DONE) {
            callback?.invoke(textview)
        }
        true
    }
}