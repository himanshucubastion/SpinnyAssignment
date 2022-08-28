package com.spinny.assignment.services.repository

import androidx.annotation.WorkerThread
import com.spinny.assignment.model.entities.Auth
import com.spinny.assignment.services.dao.AuthDao

class AuthRepo(private val authDao: AuthDao) {

    @WorkerThread
    suspend fun createUserAuth(auth: Auth) =  authDao.createUserAuth(auth)

    @WorkerThread
    suspend fun getUserAuth(auth: Auth) = authDao.getAuthDetails(auth.username, auth.password)

}
