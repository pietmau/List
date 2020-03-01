package com.pppp.travelchecklist.card.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.ColorInt
import com.pppp.travelchecklist.R

class CustomDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable
    private val mBounds = Rect()
    private val dividerSize: Int

    init {
        val typedArray = context.theme.obtainStyledAttributes(intArrayOf(R.attr.customDividerColor, R.attr.customDividerHeight))
        @ColorInt val color = typedArray.getColor(0, 0)
        mDivider = ColorDrawable(color)
        @SuppressLint("ResourceType")
        dividerSize = typedArray.getDimensionPixelSize(1, 0)
        typedArray.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }
        drawVertical(c, parent)
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + Math.round(child.translationY)
            val top = bottom - dividerSize
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, 0, 0, dividerSize)
    }

    companion object {
        private val TAG = "DividerItem"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
