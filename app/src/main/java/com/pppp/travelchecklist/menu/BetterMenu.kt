package com.pppp.travelchecklist.menu

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.UnsupportedOperationException

class BetterMenu @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle),
    BetterMenuAdapter.Callback {

    private var items: List<BetterMenuItem>
        set(value) {
            adapter = BetterMenuAdapter(items, this)
        }
        get() = throw UnsupportedOperationException()

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
    }

    override fun onItemClicked(item: BetterMenuItem) {

    }
}