package com.example.a1valettaskapp.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.common.Constants.DATABASENAME

@Database(entities = [Device::class], version = 1, exportSchema = false)
abstract class DeviceDatabase : RoomDatabase(){

    abstract fun deviceDao(): DeviceDao

    companion object{

        @Volatile private var instance: DeviceDatabase? = null
        private var LOCK = Any()

        operator fun invoke (context: Context) = instance?: synchronized(LOCK){

            instance ?: buidDatabase(context).also {
                instance = it
            }
        }

        private fun buidDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, DeviceDatabase::class.java, DATABASENAME).fallbackToDestructiveMigration().build()


    }

}
