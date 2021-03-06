package com.pppp.travelchecklist.menu

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.UnsupportedOperationException

class BetterMenu @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle),
    BetterMenuAdapter.Callback {

    var callback: ((BetterMenuItem) -> Unit)? = null

    var items: List<BetterMenuItem>
        set(value) {
            adapter = BetterMenuAdapter(value, this)
        }
        get() = throw UnsupportedOperationException()

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
    }

    override fun onItemClicked(item: BetterMenuItem) {
        callback?.invoke(item)
    }
}