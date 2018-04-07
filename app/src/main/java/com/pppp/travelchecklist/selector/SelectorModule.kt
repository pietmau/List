package com.pppp.travelchecklist.selector

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.selector.view.SelectorController
import dagger.Module
import dagger.Provides

@Module
class SelectorModule(private val activity: AppCompatActivity) {
    private val fragmentManager = activity.supportFragmentManager

    private val frag by lazy { (fragmentManager.findFragmentByTag(SelectorModuleRetainedFragment.TAG) as?SelectorModuleRetainedFragment) ?: instantiateFragment() }

    private val controller by lazy {
        frag.controller ?: SelectorController().apply { frag.controller = this }
    }

    private fun instantiateFragment(): SelectorModuleRetainedFragment =
            SelectorModuleRetainedFragment().apply {
                this@SelectorModule.fragmentManager.beginTransaction().add(this, SelectorModuleRetainedFragment.TAG).commit()
            }

    @Provides
    fun provideSelectorController() = controller

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