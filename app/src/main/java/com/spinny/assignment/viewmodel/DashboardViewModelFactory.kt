package com.spinny.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spinny.assignment.services.network.ServerCalls
import com.spinny.assignment.services.repository.AuthRepo
import com.spinny.assignment.services.repository.CarRepo

class DashboardViewModelFactory(private val repo: ServerCalls, private val carRepo: CarRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repo,carRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}