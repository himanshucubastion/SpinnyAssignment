package com.spinny.assignment.viewmodel

import androidx.lifecycle.*
import com.spinny.assignment.model.MakeCarResponse
import com.spinny.assignment.model.ModelCarResponse
import com.spinny.assignment.model.entities.MyCars
import com.spinny.assignment.services.network.ServerCalls
import com.spinny.assignment.services.repository.CarRepo
import com.spinny.assignment.utilities.OnResult
import kotlinx.coroutines.launch

class DashboardViewModel(private val serverCalls: ServerCalls, private val carRepo: CarRepo) :
    ViewModel() {


    private var _makeCarDetails: MutableLiveData<MakeCarResponse> = MutableLiveData()
    private var _modelCarDetails: MutableLiveData<ModelCarResponse> = MutableLiveData()
    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    private var _error: MutableLiveData<String> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<String> = _error
    val makeCarDetails: LiveData<MakeCarResponse> = _makeCarDetails
    val modelCarDetails: LiveData<ModelCarResponse> = _modelCarDetails


    fun getMakeCarDetails() {
        _loading.value = true
        serverCalls.getCarMakers(object : OnResult<MakeCarResponse> {
            override fun onSuccess(t: MakeCarResponse) {
                _loading.value = false
                _makeCarDetails.value = t
            }

            override fun onFailure(msg: String) {
                _loading.value = false
                _error.value = msg
            }
        })
    }

    fun getCarModels(id: String) {
        _loading.value = true
        serverCalls.getCarModels(id, object : OnResult<ModelCarResponse> {
            override fun onSuccess(t: ModelCarResponse) {
                _loading.value = false
                _modelCarDetails.value = t
            }

            override fun onFailure(msg: String) {
                _loading.value = false
                _error.value = msg
            }
        })
    }


    //val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()
    fun getMyCars(userId: Int) = carRepo.getMyCars(userId).asLiveData();

    fun createCar(myCars: MyCars) = viewModelScope.launch {
        carRepo.createCar(myCars)
    }

    fun deleteCar(id: Int) = viewModelScope.launch {
        carRepo.deleteCar(id)
    }

}