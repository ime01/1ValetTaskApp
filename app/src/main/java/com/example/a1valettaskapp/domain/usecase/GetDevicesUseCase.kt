package com.example.a1valettaskapp.domain.usecase

import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import com.example.a1valettaskapp.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetDevicesUseCase @Inject constructor (private val repository: DeviceRepository) {


    operator fun invoke (): Flow<List<Device>> = flow {

        val devices = repository.getDevices()

    }
}