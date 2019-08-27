package com.pppp.travelchecklist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.view.MenuCreator
import kotlinx.android.synthetic.main.fragment_dialog_navigation.nav_view
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
    ) = inflater.inflate(R.layout.fragment_dialog_navigation, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.with(MainModule(requireActivity())).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuCreator.initMenu(nav_view.menu, checkLists, arguments?.getString(LAST_VISITED))
        nav_view.setNavigationItemSelectedListener { item ->
            onNavItemSelected(item)
            true
        }
    }

    private fun onNavItemSelected(item: MenuItem) {
        onNavigateToExistingListClicked(item.itemId)
        dismiss()
    }

    private fun onNavigateToExistingListClicked(itemId: Int) {
        val id = checkLists[itemId].id!! // let it crash
        emit(NavigationAction.NavigateToExistingList(id))
    }

    private fun onNewListClicked() {
        emit(NavigationAction.NewList)
    }

    private fun emit(navigationAction: NavigationAction) {
        (activity as? BottomNavigationItemListener)?.onNavItemSelected(navigationAction)
    }

    interface BottomNavigationItemListener {
        fun onNavItemSelected(navigationAction: NavigationAction)
    }

    sealed class NavigationAction {
        object NewList : NavigationAction()
        data class NavigateToExistingList(val id: String) : NavigationAction()
    }

    companion object {
        fun newInstance(checkLists: List<TravelCheckListImpl>, lastList: String?) =
            BottomNavigationDrawerFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ITEMS, ArrayList(checkLists))
                    putString(LAST_VISITED, lastList)
                }
            }

        val TAG = BottomNavigationDrawerFragment::class.simpleName!!
        val ITEMS = "item"
        val LAST_VISITED = "last_visited"
    }
}