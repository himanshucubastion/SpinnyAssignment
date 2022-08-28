package com.spinny.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spinny.assignment.model.entities.Auth
import com.spinny.assignment.services.repository.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepo) : ViewModel() {

    private var _authDetails: MutableLiveData<Auth> = MutableLiveData()
    val authDetails: LiveData<Auth> = _authDetails


    fun getAuthDetails(auth: Auth) = viewModelScope.launch {
        _authDetails.value = repo.getUserAuth(auth)
    }

    fun createAuth(auth: Auth) = viewModelScope.launch {
        repo.createUserAuth(auth)

    }


}