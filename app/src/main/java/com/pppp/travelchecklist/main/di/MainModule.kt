package com.pppp.travelchecklist.main.di

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.main.model.BetterOldModelImpl
import com.pppp.travelchecklist.main.model.OldModel
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.OldMainPresenter
import com.pppp.travelchecklist.model.dao.DeserializerImpl
import com.pppp.travelchecklist.model.dao.ListDao
import com.pppp.travelchecklist.model.dao.ListDaoSqlite
import com.pppp.travelchecklist.model.dao.Querymaker
import com.pppp.travelchecklist.model.database.OpenHelper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Module
class MainModule(private val activity: FragmentActivity) {

    private val fragment: RetainedFragment by lazy {
        val fragmentManager = activity?.supportFragmentManager
        var fragment: RetainedFragment? = fragmentManager?.findFragmentByTag(RetainedFragment.TAG) as? RetainedFragment
        if (fragment == null) {
            fragment = RetainedFragment()
            fragmentManager?.beginTransaction()?.add(fragment, RetainedFragment.TAG)?.commit()
            fragment?.oldModel = BetterOldModelImpl(getDao(activity.applicationContext), Schedulers.io(), AndroidSchedulers.mainThread())
        }
        fragment!!
    }

    private fun getDao(context: Context): ListDao =
        ListDaoSqlite(OpenHelper.DATABASE(context), Querymaker(), DeserializerImpl())

    @Provides
    fun providePresenter() = MainPresenter()


    @Provides
    fun provideOldPresenter(oldModel: OldModel) =
        OldMainPresenter(oldModel, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideOldModel(): OldModel = getOldModel()

    private fun getOldModel(): OldModel = fragment.oldModel

}


class RetainedFragment : Fragment() {
    lateinit var oldModel: OldModel

    companion object {
        val TAG = RetainedFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }
}
