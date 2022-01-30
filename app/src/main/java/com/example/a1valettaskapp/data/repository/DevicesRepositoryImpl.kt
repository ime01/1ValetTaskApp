package com.example.a1valettaskapp.data.repository

import androidx.lifecycle.LiveData
import com.example.a1valettaskapp.common.Resource
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.data.roomdb.DeviceDao
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow


class DevicesRepositoryImpl (val deviceDao: DeviceDao) : DeviceRepository {

    override fun getDevices(): Flow<List<Device>> {
        return  deviceDao.getDevices()
    }

    override suspend fun insertDevice(device: Device) {
        deviceDao.insert(device)
    }

    override fun searchDevice(searchQuery: String): Flow<List<Device>> {
      return deviceDao.searchDevices(searchQuery)
    }

    override suspend fun insertListOfDevices(devices: List<Device>) {
        insertListOfDevices(devices)
    }

    override suspend fun updateDevice(device: Device) {
        deviceDao.update(device)
    }

    override suspend fun deleteDevice(device: Device) {
       deviceDao.delete(device)
    }

    override fun observeAllDrinks(): LiveData<List<Device>> {
        return deviceDao.getDevicesLiveDataType()
    }

    override fun deleteAllDevices() {
        deviceDao.deleteAll()
    }


}