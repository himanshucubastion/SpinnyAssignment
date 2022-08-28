package com.spinny.assignment.services.network

import com.spinny.assignment.utilities.URLS.SERVER_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = getOkHttpClient()?.let {
                Retrofit.Builder()
                    .baseUrl(SERVER_BASE_URL)
                    .client(it)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient? {
        return OkHttpClient().newBuilder()
            .connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS)
            .build()
    }


}