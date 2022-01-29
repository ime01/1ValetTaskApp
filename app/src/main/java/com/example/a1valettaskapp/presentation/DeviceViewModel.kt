package com.example.a1valettaskapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a1valettaskapp.common.Resource
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import com.example.a1valettaskapp.domain.usecase.DeleteDeviceUseCase
import com.example.a1valettaskapp.domain.usecase.GetDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeviceViewModel @Inject constructor(private val deviceRepository: DeviceRepository) : ViewModel() {

    //Using a flow to expose data to the views in the fragmment

    private val _devicesFromSimulatedNetworkCall = deviceRepository.getDevices()

    val devicesFromSimulatedNetworkCall: Flow<List<Device>>
        get() = _devicesFromSimulatedNetworkCall


   /* fun getDevices() : Flow<List<Device>>{

        viewModelScope.launch{
            delay(2000)
           _devicesFromSimulatedNetworkCall. =  deviceRepository.getDevices()
        }
    }*/



    fun insertListOfDevices(devices:List<Device> ){

        viewModelScope.launch (Dispatchers.IO){
            deviceRepository.insertListOfDevices(devices)
        }
    }

    fun insertDevice(device: Device){

        viewModelScope.launch (Dispatchers.IO){
            deviceRepository.insertDevice(device)
        }
    }


    fun searchDevice(searchQuery: String): LiveData<List<Device>> {
        return deviceRepository.searchDevice(searchQuery).asLiveData()

    }


    fun updateDevice(device: Device){
        viewModelScope.launch (Dispatchers.IO){
            deviceRepository.updateDevice(device)
        }
    }

    fun deleteDevice(device: Device){
        viewModelScope.launch (Dispatchers.IO){
            deviceRepository.deleteDevice(device)
        }
    }





}