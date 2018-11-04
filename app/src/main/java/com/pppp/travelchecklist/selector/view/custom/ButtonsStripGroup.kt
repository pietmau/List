package com.pppp.travelchecklist.selector.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import android.widget.ToggleButton
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.getChildren
import kotlinx.android.synthetic.main.button_strip.view.*

class ButtonsStripGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ButtonsStrip(context, attrs, defStyleAttr, defStyleRes), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    var listener: Listener? = null

    override fun createButton(item: TagImpl) =
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
        val item = buttonView?.tag as? TagImpl
        item ?: return
        if (isChecked) {
            listener?.onItemSelected(item)
        } else {
            listener?.onItemDeselected(item)
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