package com.pppp.travelchecklist.list.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.pppp.travelchecklist.list.viewmodel.CheckListViewModel
import com.pppp.travelchecklist.list.viewmodel.FirebaseCheckListViewModel
import com.pppp.travelchecklist.login.LoginViewModel
import com.pppp.travelchecklist.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewCheckListModule(private val listId: String, private val activity: FragmentActivity) {

    @Provides
    fun provideViewModel(): CheckListViewModel = ViewModelProviders.of(
        activity,
        ViewCheckListViewModelFactory(listId)
    ).get(FirebaseCheckListViewModel::class.java)

}

class ViewCheckListViewModelFactory(private val listId: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FirebaseCheckListViewModel(listId = listId) as T
}