package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.view.SelectorController
import dagger.Module
import dagger.Provides

@Module
class SelectorModule(private val activity: FragmentActivity) {
    private val fragmentManager = activity.supportFragmentManager

    private val frag by lazy {
        (fragmentManager.findFragmentByTag(SelectorModuleRetainedFragment.TAG) as?SelectorModuleRetainedFragment)
                ?: instantiateFragment()
    }

    private val controller by lazy {
        //TODO use ViewModel instead!
        frag.controller ?: SelectorController().apply { frag.controller = this }
    }

    private fun instantiateFragment(): SelectorModuleRetainedFragment =
        SelectorModuleRetainedFragment().apply {
            this@SelectorModule.fragmentManager.beginTransaction()
                .add(this, SelectorModuleRetainedFragment.TAG).commit()
        }

    @Provides
    fun provideSelectorController() = controller

    @Provides
    fun provideSelectorPresenter() =
        ViewModelProviders.of(activity).get(SelectorPresenter::class.java)

    class SelectorModuleRetainedFragment : Fragment() {
        var controller: SelectorController? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        companion object {
            val TAG = SelectorModuleRetainedFragment::class.simpleName
        }
    }

}