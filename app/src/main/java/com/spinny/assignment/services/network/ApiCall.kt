package com.spinny.assignment.services.network

import com.spinny.assignment.model.MakeCarResponse
import com.spinny.assignment.model.ModelCarResponse
import com.spinny.assignment.utilities.KEYS.GET_ALL_MAKERS
import com.spinny.assignment.utilities.KEYS.GET_MODEL_CAR
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiCall {

    @GET(GET_ALL_MAKERS)
    fun getCarMaker(): Call<MakeCarResponse>

    @GET("${GET_MODEL_CAR}/{id}/?format=json")
    fun getCarModels(@Path("id") id: String): Call<ModelCarResponse>
}