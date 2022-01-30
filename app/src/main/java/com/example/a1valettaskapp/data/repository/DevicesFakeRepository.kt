package com.example.a1valettaskapp.data.repository

import androidx.lifecycle.LiveData
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.data.roomdb.DeviceDao
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevicesFakeRepository @Inject constructor (private val dbReference : DeviceDao):DeviceRepository{

    override fun getDevices(): Flow<List<Device>> {
       return dbReference.getDevices()
    }

    override suspend fun insertDevice(device: Device) {
        dbReference.insert(device)
    }

    override fun searchDevice(searchQuery: String): Flow<List<Device>> {
       return dbReference.searchDevices(searchQuery)
    }

    override suspend fun insertListOfDevices(devices: List<Device>) {
        dbReference.insertDeviceList(devices)
    }

    override suspend fun updateDevice(device: Device) {
        dbReference.update(device)
    }

    override suspend fun deleteDevice(device: Device) {
        dbReference.delete(device)
    }


    override fun observeAllDrinks(): LiveData<List<Device>> {
        return dbReference.getDevicesLiveDataType()
    }

    override fun deleteAllDevices() {
        dbReference.deleteAll()
    }

}
