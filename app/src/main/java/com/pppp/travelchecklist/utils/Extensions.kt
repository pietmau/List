package com.pppp.travelchecklist.utils

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.application.di.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.toolbar

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

fun View.getChildren(): List<View> {
    if (this !is ViewGroup) {
        return emptyList()
    }
    return (0..this.childCount).map { this.getChildAt(it) }.filterNotNull()
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

val Activity.appComponent: AppComponent
    get() = (this.application as App).appComponent

val Fragment.appComponent: AppComponent
    get() = requireActivity().appComponent

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

fun Fragment.getStringArgument(key: String) = arguments?.getString(key)

fun Fragment.requireStringArgument(key: String) = requireNotNull(arguments?.getString(key))

val Context.isDev get() = Settings.Secure.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1

fun <K, V> Map<K, V>.replaceSameKeyItemsWith(newElements: Map<K, V>?): Map<K, V> {
    val result = this.toMutableMap()
    newElements?.entries?.forEach { (key, value) -> result[key] = value }
    return result.toMap()
}

fun Fragment.getColor(@ColorRes colour: Int) = ResourcesCompat.getColor(resources, colour, context?.theme)

var EditText.textAsAString: String?
    get() = text?.toString() ?: ""
    set(value) {
        val currentText = text?.toString()
        if (currentText != value) {
            setText(value, TextView.BufferType.EDITABLE)
        }
    }

fun EditText.setAfterChangeListener(listener: (String?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener(s?.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            /* NoOp */
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            /* NoOp */
        }
    })
}

val Context.notificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

fun Activity.setTitleAndSubtitle(title: String, subTitle: String) {
    toolbar.title = title
    toolbar.subtitle = subTitle
}

val View.isMarshmallowOrAbove
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@ColorInt
fun Context.geColorFromTheme(@AttrRes color: Int): Int {
    val resolvedAttr = TypedValue()
    theme.resolveAttribute(color, resolvedAttr, true)
    val colorRes = resolvedAttr.run { if (resourceId != 0) resourceId else data }
    return ContextCompat.getColor(this, colorRes)
}

val <T> T.exhaustive: T
    get() = this