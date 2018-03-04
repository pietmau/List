package com.pppp.travelchecklist.main.di

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.model.ModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class MainModule(private val activity: AppCompatActivity) {

    @Provides
    fun providePresenter(model: Model): MainPresenter = getPresenter(model)


    @Provides
    fun provideModel(): Model = ModelImpl()

    private fun getPresenter(model: Model): MainPresenter {
        val supportFragmentManager = activity.supportFragmentManager
        var frag: RetainedFragment? = supportFragmentManager.findFragmentByTag(RetainedFragment.TAG) as? RetainedFragment
        if (frag == null) {
            frag = RetainedFragment()
            supportFragmentManager.beginTransaction().add(frag, RetainedFragment.TAG).commit()
            frag.data = MainPresenter(model)
        }
        return frag.data!!
    }

    class RetainedFragment : Fragment() {
        var data: MainPresenter? = null

        companion object {
            val TAG = RetainedFragment::class.simpleName
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }
    }

}