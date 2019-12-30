package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_schedule_view.view.text

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    lateinit var callback: Callback

    init {
        LayoutInflater.from(context).inflate(R.layout.view_schedule_view, this, true);
        text.setOnClickListener {
            callback()
        }
    }

}
typealias Callback = () -> Unit
