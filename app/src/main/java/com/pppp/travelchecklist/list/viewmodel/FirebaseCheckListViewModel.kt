package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.login.LoginViewModel
import com.pppp.travelchecklist.repository.FirebaseTravelChecklistRepository
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import javax.inject.Inject

class FirebaseCheckListViewModel(
    private val listId: String,
    private val repo: TravelChecklistRepository
) : CheckListViewModel, ViewModel() {

    override val states: MutableLiveData<CheckListViewModel.ViewState> = MutableLiveData<CheckListViewModel.ViewState>()

    init {
        repo.getUserCheckListAndUpdates(listId, success = {
            states.postValue(CheckListViewModel.ViewState.Data(it))
        })
    }

    override fun push(t: CheckListViewModel.ViewEvent) {

    }

}