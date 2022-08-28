package com.spinny.assignment

import android.app.Application
import com.spinny.assignment.db.SDataBase
import com.spinny.assignment.services.network.APIClient
import com.spinny.assignment.services.network.ApiCall
import com.spinny.assignment.services.network.ServerCalls
import com.spinny.assignment.services.repository.AuthRepo
import com.spinny.assignment.services.repository.CarRepo

class AppController : Application() {
    private val database by lazy { SDataBase.getDatabase(this) }
    val repository by lazy { AuthRepo(database.authDao()) }
    val carRepo by lazy { CarRepo(database.carDao()) }

    private val apiCall by lazy { APIClient.getClient()?.create(ApiCall::class.java) }
    val serverCalls by lazy {
        apiCall?.let {
            ServerCalls(it)
        }
    }


}