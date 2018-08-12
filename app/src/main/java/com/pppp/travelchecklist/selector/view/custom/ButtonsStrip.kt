package com.pppp.travelchecklist.selector.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.button_strip.view.*
import org.jetbrains.anko.collections.forEachWithIndex


class ButtonsStrip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), View.OnClickListener {

    private var items = mutableListOf<Item>()
    var callback: Callback? = null

    var title: String?
        set(value) {
            text.text = value
        }
        get() = text.text?.toString()

    private val layoutInflater
        get() = LayoutInflater.from(context)

    init {
        layoutInflater.inflate(R.layout.button_strip, this, true);
        gravity = Gravity.CENTER
        orientation = LinearLayout.VERTICAL
    }

    fun setItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        items.forEachWithIndex { index, item -> addItem(item, index) }
    }

    private fun addItem(item: Item, index: Int) {
        val button = createButton(item)
        button.id = index
        button.tag = item
        box.addView(button)
    }

    private fun createButton(item: Item) =
        (layoutInflater.inflate(R.layout.toggle_button, null) as ToggleButton)
            .apply {
                text = item.description
                textOff = item.description
                textOn = item.description
                setOnClickListener(this@ButtonsStrip)
            }

    override fun onClick(v: View?) {
        ((v as ToggleButton)?.getTag() as? Item)?.let { callback?.onItemSelected(it) }
    }

    data class Item(val description: String)

    interface Callback {
        fun onItemSelected(item: Item)
    }
}