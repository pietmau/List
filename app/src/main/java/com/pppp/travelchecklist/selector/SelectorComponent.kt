package com.pppp.travelchecklist.selector

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SelectorModule::class))
interface SelectorComponent {

    fun inject(selectorView: SelectorView)
}