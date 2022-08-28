package com.spinny.assignment.services.repository

import androidx.annotation.WorkerThread
import com.spinny.assignment.model.entities.MyCars
import com.spinny.assignment.services.dao.CarDao
import kotlinx.coroutines.flow.Flow

class CarRepo(private val carDao: CarDao) {

    @WorkerThread
    suspend fun createCar(myCars: MyCars) = carDao.createCar(myCars)
    @WorkerThread
    suspend fun deleteCar(id: Int) = carDao.deleteCar(id)

    fun getMyCars(userId: Int): Flow<List<MyCars>> = carDao.getMyCars(userId)


}