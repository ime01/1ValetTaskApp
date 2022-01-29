package com.example.a1valettaskapp.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a1valettaskapp.data.model.Device
import kotlinx.coroutines.flow.Flow


@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (device: Device)

    @Query("SELECT * FROM device_table  WHERE name LIKE :searchQuery")
    fun searchDevices(searchQuery: String): Flow<List<Device>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeviceList (devices: List<Device>)

    @Update
    suspend fun  update(device: Device)

    @Query("SELECT * FROM  device_table")
    fun getDevices(): Flow<List<Device>>
    //fun getDevices(): LiveData<List<HouseWordsModel>>

    @Delete
    suspend fun delete (device: Device)
}