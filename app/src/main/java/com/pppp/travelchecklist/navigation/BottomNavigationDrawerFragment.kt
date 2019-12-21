package com.pppp.travelchecklist.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.model.MainUseCase
import com.pppp.travelchecklist.menu.BetterMenuItem
import kotlinx.android.synthetic.main.fragment_dialog_navigation.nav_view
import javax.inject.Inject

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    @Inject
    lateinit var menuCreator: MenuCreator
    @Inject
    lateinit var mainUseCase: MainUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_navigation, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.with(MainModule(requireActivity())).inject(this)
        mainUseCase.getUsersLists().observe(requireActivity(), Observer { lists ->
            nav_view.items = menuCreator.getItems(lists?.filterNotNull() ?: emptyList(), arguments?.getString(LAST_VISITED))
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nav_view.callback = ::onItemClicked
    }

    private fun onItemClicked(item: BetterMenuItem) {
        onNavigateToExistingListClicked(item.id)
        dismiss()
    }

    private fun onNavigateToExistingListClicked(itemId: String) {
        emit(NavigationAction.NavigateToExistingList(itemId))
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
        fun newInstance(lastList: Long) =
            BottomNavigationDrawerFragment().apply {
                arguments = Bundle().apply {
                    putLong(LAST_VISITED, lastList)
                }
            }

        val TAG = BottomNavigationDrawerFragment::class.simpleName!!
        val LAST_VISITED = "last_visited"
    }
}