package com.spinny.assignment.services.network

import com.spinny.assignment.model.MakeCarResponse
import com.spinny.assignment.model.ModelCarResponse
import com.spinny.assignment.utilities.OnResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerCalls(private val apiCall: ApiCall) {


    fun getCarMakers(onResult: OnResult<MakeCarResponse>) {
        apiCall.getCarMaker().enqueue(object : Callback<MakeCarResponse> {
            override fun onResponse(
                call: Call<MakeCarResponse>,
                response: Response<MakeCarResponse>
            ) {
                onResult.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<MakeCarResponse>, t: Throwable) {
                onResult.onFailure(t.message!!)
            }
        })
    }

    fun getCarModels(id: String, onResult: OnResult<ModelCarResponse>) {
        apiCall.getCarModels(id).enqueue(object : Callback<ModelCarResponse> {
            override fun onFailure(call: Call<ModelCarResponse>, t: Throwable) {
                onResult.onFailure(t.message!!)
            }

            override fun onResponse(
                call: Call<ModelCarResponse>,
                response: Response<ModelCarResponse>
            ) {
                onResult.onSuccess(response.body()!!)
            }
        })
    }

}