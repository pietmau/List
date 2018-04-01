package com.pppp.travelchecklist.main.di

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.model.ModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.model.dao.DeserializerImpl
import com.pppp.travelchecklist.model.dao.ListDao
import com.pppp.travelchecklist.model.dao.ListDaoSqlite
import com.pppp.travelchecklist.model.dao.Querymaker
import com.pppp.travelchecklist.model.database.OpenHelper
import dagger.Module
import dagger.Provides


@Module
class MainModule(private val activity: FragmentActivity) {

    private val fragment: RetainedFragment by lazy {
        val fragmentManager = activity?.supportFragmentManager
        var fragment: RetainedFragment? = fragmentManager?.findFragmentByTag(RetainedFragment.TAG) as? RetainedFragment
        if (fragment == null) {
            fragment = RetainedFragment()
            fragmentManager?.beginTransaction()?.add(fragment, RetainedFragment.TAG)?.commit()
            fragment?.model = ModelImpl(getDao(activity.applicationContext))
        }
        fragment!!
    }

    private fun getDao(context: Context): ListDao = ListDaoSqlite(OpenHelper.DATABASE(context), Querymaker(), DeserializerImpl())

    @Provides
    fun providePresenter(model: Model) = MainPresenter(model)

    @Provides
    fun provideModel(): Model = getModel()

    private fun getModel(): Model = fragment.model

}


class RetainedFragment : Fragment() {
    lateinit var model: Model

    companion object {
        val TAG = RetainedFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }
}
