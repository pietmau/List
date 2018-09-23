package com.pppp.travelchecklist.selector.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
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
        (layoutInflater.inflate(R.layout.toggle_button, null) as ToggleButton)
            .apply {
                text = item.title
                textOff = item.title
                textOn = item.title
                setOnCheckedChangeListener(this@ButtonsStripGroup)
            }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        deselectAll()
        buttonView?.isChecked = isChecked
        val item = buttonView?.tag as? Item
        item ?: return
        if (isChecked) {
            listener?.onItemSelected(item)
        } else {
            listener?.onItemDeselected(item)
        }
    }

    private fun deselectAll() {
        for (i in 0 .. childCount) {
            (box.getChildAt(i) as? ToggleButton)?.isChecked = false
        }
    }

    override fun onClick(v: View?) {/*NoOp*/
    }

    interface Listener {
        fun onItemSelected(item: Item)
        fun onItemDeselected(item: Item)
    }
}