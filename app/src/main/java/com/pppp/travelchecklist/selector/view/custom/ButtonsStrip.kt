package com.pppp.travelchecklist.selector.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.pppp.entities.pokos.Tag
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.button_strip.view.*
import org.jetbrains.anko.collections.forEachWithIndex

open class ButtonsStrip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var items = mutableListOf<Tag>()
    var callback: Callback? = null

    var title: String?
        set(value) {
            text.text = value
        }
        get() = text.text?.toString()

    protected val layoutInflater
        get() = LayoutInflater.from(context)

    init {
        layoutInflater.inflate(R.layout.button_strip, this, true);
        gravity = Gravity.CENTER
        orientation = LinearLayout.VERTICAL
    }

    fun setItems(items: List<Tag>) {
        this.items.clear()
        this.items.addAll(items)
        items.forEachWithIndex { index, item -> addItem(item, index) }
    }

    private fun addItem(item: Tag, index: Int) {
        val button = createButton(item)
        button.id = index
        button.tag = item
        box.addView(button)
    }

    protected open fun createButton(item: Tag) =
        (layoutInflater.inflate(R.layout.toggle_button, null) as ToggleButton)
            .apply {
                text = item.title
                textOff = item.title
                textOn = item.title
                setOnCheckedChangeListener { view, checked ->
                    OnCheckedChange(view, checked)
                }
            }

    private fun OnCheckedChange(view: CompoundButton?, checked: Boolean) {
        val item = (view as ToggleButton).getTag() as? Tag
        item ?: return
        if (checked) {
            callback?.onItemSelected(item)
        } else {
            callback?.onItemDeSelected(item)
        }
    }

    fun setItemsSelected(tags: List<Pair<Tag, Boolean>>) {
        val map = tags.toMap()
        (0..box.childCount - 1)
            .map { box.getChildAt(it) as CompoundButton }
            .map {
                val key = it.tag as? Tag
                it.isChecked = map.get(key) ?: false
            }
    }

    interface Callback {
        fun onItemSelected(item: Tag)
        fun onItemDeSelected(item: Tag)
    }
}