package com.pppp.travelchecklist.newlist.view.viewpager.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.viewmodel.CreateChecklistView
import kotlinx.android.synthetic.main.fragment_getname.edittext

class NameFragment : Fragment() {
    private val callback
        get() = (requireActivity() as CreateChecklistView).selectionCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_getname, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                callback?.onNameChanged(edittext.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*NoOp*/
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*NoOp*/
            }
        })
    }

    fun setNameInputError(nameInputError: String?) {
        edittext?.error = nameInputError
        if (nameInputError != null) {
            edittext?.requestFocus()
        }
    }
}
