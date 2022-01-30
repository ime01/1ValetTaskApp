package com.example.a1valettaskapp.presentation


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a1valettaskapp.common.Event
import com.example.a1valettaskapp.common.Resource
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.data.repository.DevicesFakeRepository
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceViewModelForTesting @Inject constructor (private var deviceRepository: DeviceRepository): ViewModel() {

    private val _insertDeviceItemStatus = MutableLiveData<Event<Resource<Device>>>()
    val insertDeviceItemStatus: LiveData<Event<Resource<Device>>> = _insertDeviceItemStatus

    val devicesFromLocalDb = deviceRepository.observeAllDrinks()

   suspend fun insert (device: Device){
       deviceRepository.insertDevice(device)
    }

    suspend fun delete (device: Device){
        deviceRepository.deleteDevice(device)
    }

    suspend fun clearAll (){
        deviceRepository.deleteAllDevices()
    }


}