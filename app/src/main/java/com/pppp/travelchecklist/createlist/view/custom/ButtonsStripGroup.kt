package com.pppp.travelchecklist.createlist.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.getChildren
import kotlinx.android.synthetic.main.button_strip.view.*

class ButtonsStripGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ButtonsStrip(context, attrs, defStyleAttr, defStyleRes), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    var listener: Listener? = null

    override fun createButton(item: Tag) =
        (LayoutInflater.from(context).inflate(R.layout.toggle_button, null) as ToggleButton)
            .apply {
                text = item.title
                textOff = item.title
                textOn = item.title
                setOnCheckedChangeListener(this@ButtonsStripGroup)
            }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        deselectAll()
        buttonView?.isChecked = isChecked
        val item = buttonView?.tag as? TagImpl
        item ?: return
        if (isChecked) {
            listener?.onItemSelected(item)
        } else {
            listener?.onItemDeselected(item)
        }
    }

    override fun setItemsSelected(tags: List<Pair<Tag, Boolean>>) {
        val map = tags.toMap()
        (0..box.childCount - 1)
            .map { box.getChildAt(it) as CompoundButton }
            .map {
                val key = it.tag as? Tag
                it.setOnCheckedChangeListener(null)
                it.isChecked = map.get(key) ?: false
                it.setOnCheckedChangeListener(this)
            }
    }

    private fun deselectAll() {
        box.getChildren()
            .map { it as? ToggleButton }
            .filterNotNull()
            .onEach { it.isChecked = false }
    }

    override fun onClick(v: View?) {/*NoOp*/
    }

    interface Listener {
        fun onItemSelected(item: TagImpl)
        fun onItemDeselected(item: TagImpl)
    }
}