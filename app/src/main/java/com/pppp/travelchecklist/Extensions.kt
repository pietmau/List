package com.pppp.travelchecklist

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
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pppp.travelchecklist.editcard.EditCategoryBottomDialog

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

inline fun <reified T : Fragment> FragmentActivity.findFragmentById(@IdRes id: Int): T? {
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

val View.isMarshmallowOrAbove
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager?.getActiveNetworkInfo()
    return activeNetworkInfo?.isConnected() == true
}

fun <K, V> lazyMap(initializer: (K) -> V): Map<K, V> {
    val map = mutableMapOf<K, V>()
    return map.withDefault { key ->
        val newValue = initializer(key)
        map[key] = newValue
        return@withDefault newValue
    }
}

fun Activity.showConfirmationDialog(yes: (() -> Unit)?) {
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.add_list)
        .setMessage(R.string.confirm_add_new_list)
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

fun Fragment.getString(key: String) = arguments?.getString(key)

fun Fragment.getLong(key: String) = arguments?.getLong(key)