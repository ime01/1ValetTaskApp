package com.example.a1valettaskapp.domain.usecase

import android.util.Log
import com.example.a1valettaskapp.common.Constants.DELETEUSECASERROR
import com.example.a1valettaskapp.common.Resource
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteDeviceUseCase  @Inject constructor (private val repository: DeviceRepository){

    suspend operator fun invoke (device: Device) {

        try {
            repository.deleteDevice(device)

        }catch (e:Exception){

          Log.e(DELETEUSECASERROR, e.localizedMessage)
        }

    }
}