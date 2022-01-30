package com.example.a1valettaskapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.a1valettaskapp.common.Resource
import com.example.a1valettaskapp.data.model.Device
import kotlinx.coroutines.flow.Flow


interface DeviceRepository {

    fun getDevices() : Flow<List<Device>>

    suspend fun insertDevice(device: Device)

    fun searchDevice(searchQuery: String): Flow<List<Device>>

    suspend fun insertListOfDevices(devices: List<Device>)

    suspend fun updateDevice(device: Device)

    suspend fun deleteDevice(device: Device)

    fun observeAllDrinks(): LiveData<List<Device>>

    fun deleteAllDevices()
}