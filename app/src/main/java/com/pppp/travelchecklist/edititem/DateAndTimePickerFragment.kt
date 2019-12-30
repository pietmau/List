package com.pppp.travelchecklist.edititem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.fragment_dialog_dtate_and_time_picker.picker

class DateAndTimePickerFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_dialog_dtate_and_time_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //picker.initializePicker()
    }

    companion object {
        val TAG = DateAndTimePickerFragment::class.simpleName!!
    }
}