package com.pppp.travelchecklist.selector

import com.pppp.travelchecklist.selector.view.SelectorFragment
import com.pppp.travelchecklist.selector.view.SelectorView
import com.pppp.travelchecklist.selector.view.viewpager.WhereAreYouFlyingFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SelectorModule::class))
interface SelectorComponent {

    fun inject(selectorView: SelectorView)

    fun inject(selectorView: SelectorFragment)

    fun inject(whereAreYouFlyingFragment: WhereAreYouFlyingFragment)
}