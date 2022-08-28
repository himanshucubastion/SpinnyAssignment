package com.spinny.assignment.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spinny.assignment.model.entities.MyCars
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createCar(myCars: MyCars)

    @Query("SELECT * FROM car where userId = :userId")
    fun getMyCars(userId: Int): Flow<List<MyCars>>

    @Query("DELETE FROM car where id = :id")
    suspend fun deleteCar(id: Int)

}