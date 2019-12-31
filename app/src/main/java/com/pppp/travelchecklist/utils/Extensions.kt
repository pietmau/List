package com.pppp.travelchecklist.utils

import android.app.Activity
import android.content.Context
import android.os.Build
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
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pppp.travelchecklist.R

inline fun <T : View> View.findViewByIdLazy(@IdRes id: Int): Lazy<T> = lazy {
    findViewById<T>(id)
}

inline fun <reified T : Fragment> FragmentActivity.findAddedFragment(@IdRes id: Int): T? {
    val fragment = supportFragmentManager.findFragmentById(id) ?: return null
    return when {
        fragment !is T -> null
        !fragment.isAdded -> null
        else -> fragment
    }
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


val View.isMarshmallowOrAbove
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M


fun Activity.showConfirmationDialog(yes: (() -> Unit)?, title: Int, message: Int) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.yes, { _, _ ->
            yes?.invoke()
        })
        .setNegativeButton(android.R.string.cancel, { _, _ -> })
        .create().show()
}

fun Activity.showDialog(
    @StringRes title: Int,
    @StringRes message: Int? = null,
    @StringRes ok: Int = R.string.ok,
    @StringRes cancel: Int = R.string.cancel,
    negative: (() -> Unit)? = null,
    positive: (() -> Unit)?
) {
    val dialogBuilder = MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setPositiveButton(ok, { _, _ -> positive?.invoke() })
        .setNegativeButton(cancel, { _, _ -> negative?.invoke() })
    message?.let { dialogBuilder.setMessage(message) }
    dialogBuilder.create().show()
}

fun Fragment.showDialog(
    @StringRes title: Int,
    @StringRes message: Int? = null,
    @StringRes ok: Int = R.string.ok,
    @StringRes cancel: Int = R.string.cancel,
    negative: (() -> Unit)? = null,
    positive: (() -> Unit)?
) = requireActivity().showDialog(title, message, ok, cancel, negative, positive)

val <T> T.exhaustive: T
    get() = this

fun Fragment.getStringArgument(key: String) = arguments?.getString(key)

fun Fragment.requireStringArgument(key: String) = requireNotNull(arguments?.getString(key))

val Context.isDev get() = Settings.Secure.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1

fun <K, V> Map<K, V>.replaceSameKeyItemsWith(newElements: Map<K, V>?): Map<K, V> {
    val result = this.toMutableMap()
    newElements?.entries?.forEach { (key, value) -> result[key] = value }
    return result.toMap()
}

fun Fragment.getColor(@ColorRes colour: Int) = ResourcesCompat.getColor(resources, android.R.color.white, context?.theme)


val EditText.textAsAString
    get() = text?.toString() ?: ""