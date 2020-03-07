package com.pppp.travelchecklist.list.di

import com.pppp.travelchecklist.list.view.CheckListFragment
import dagger.Subcomponent

@CheckListFragmentScope
@Subcomponent(modules = [CheckListFragmentModule::class])
interface CheckListFragmentComponent {
    fun inject(checkListFragment: CheckListFragment)
}