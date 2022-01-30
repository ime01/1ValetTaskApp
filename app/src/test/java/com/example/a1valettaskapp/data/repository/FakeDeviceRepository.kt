package com.flowz.drinkcocktails.drinksrepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeDeviceRepository: DeviceRepository {

    private val deviceItems = mutableListOf<Device>()

    private val observableDeviceItems = MutableLiveData<List<Device>>(deviceItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableDeviceItems.postValue(deviceItems)
    }


    override fun getDevices(): Flow<List<Device>> = flow{
        return@flow
    }

    override suspend fun insertDevice(device: Device) {
        deviceItems.add(device)
        refreshLiveData()
    }

    override fun searchDevice(searchQuery: String): Flow<List<Device>>  = flow{
        return@flow
    }

    override suspend fun insertListOfDevices(devices: List<Device>) {
        Log.d("insert", "insert many devices here")
    }

    override suspend fun updateDevice(device: Device) {
        Log.d("update", "update device here")
    }

    override suspend fun deleteDevice(device: Device) {
        deviceItems.remove(device)
        refreshLiveData()
    }

    override fun observeAllDrinks(): LiveData<List<Device>> {
        return observableDeviceItems
    }

    override fun deleteAllDevices() {
        deviceItems.clear()
        refreshLiveData()
    }


}