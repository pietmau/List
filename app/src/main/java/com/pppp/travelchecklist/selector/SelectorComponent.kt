package com.pppp.travelchecklist.selector

import com.pppp.travelchecklist.selector.view.SelectorView
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SelectorModule::class))
interface SelectorComponent {

    fun inject(selectorView: SelectorView)
}