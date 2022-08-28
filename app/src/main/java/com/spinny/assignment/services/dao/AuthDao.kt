package com.spinny.assignment.services.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.spinny.assignment.model.entities.Auth

@Dao
interface AuthDao {

    @Insert(onConflict = IGNORE)
    suspend fun createUserAuth(auth: Auth)

    @Query("SELECT * FROM auth WHERE username = :usr AND password = :pass")
    suspend fun getAuthDetails(usr: String, pass: String): Auth

}