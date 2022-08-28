package com.spinny.assignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spinny.assignment.model.entities.Auth
import com.spinny.assignment.model.entities.MyCars
import com.spinny.assignment.services.dao.AuthDao
import com.spinny.assignment.services.dao.CarDao

@Database(entities = [Auth::class, MyCars::class], version = 1, exportSchema = false)
abstract class SDataBase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: SDataBase? = null
        fun getDatabase(context: Context): SDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SDataBase::class.java,
                    "s_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}