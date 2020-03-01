package com.pppp.travelchecklist.card.recycler

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {
    private var automeasure = true

    override fun onAdapterChanged(oldAdapter: RecyclerView.Adapter<*>?, newAdapter: RecyclerView.Adapter<*>?) {
        newAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                automeasure = false
            }
        })
        removeAllViews()
    }

    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
        if (!isAutoMeasureEnabled) {
            requestSimpleAnimationsInNextLayout();
            setMeasuredDimension(getWidth(), getHeight());
        }
    }

    override fun onItemsRemoved(recyclerView: RecyclerView, positionStart: Int, itemCount: Int) {
        super.onItemsRemoved(recyclerView, positionStart, itemCount)
        postOnAnimation {
            recyclerView.itemAnimator?.isRunning {
                automeasure = true
                requestLayout()
            }
        }
    }

    override fun isAutoMeasureEnabled() = automeasure
}