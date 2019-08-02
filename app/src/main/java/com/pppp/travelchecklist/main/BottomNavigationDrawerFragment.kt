package com.pppp.travelchecklist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.view.MenuCreator
import kotlinx.android.synthetic.main.fragment_navigation_view.nav_view
import javax.inject.Inject

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var menuCreator: MenuCreator

    private val checkLists: List<TravelCheckListImpl> by lazy {
        arguments?.getParcelableArrayList(ITEMS) ?: emptyList<TravelCheckListImpl>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_navigation_view, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.with(MainModule(requireActivity())).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuCreator.initMenu(nav_view.menu, checkLists)
        nav_view.setNavigationItemSelectedListener { item -> true }
    }

    companion object {
        fun newInstance(checkLists: List<TravelCheckListImpl>) =
            BottomNavigationDrawerFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ITEMS, ArrayList(checkLists))
                }
            }

        val TAG = BottomNavigationDrawerFragment::class.simpleName!!
        val ITEMS = "item"
    }
}