package com.pppp.uploader

import dagger.Component

@Component(modules = arrayOf(Module::class))
interface UploaderComponent {
    fun inject(mainActivity: MainActivity)
}