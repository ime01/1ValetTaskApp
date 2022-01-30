package com.example.a1valettaskapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.data.repository.DevicesFakeRepository
import com.flowz.drinkcocktails.drinksrepository.FakeDeviceRepository
import com.flowz.drinkcocktails.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DeviceViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DeviceViewModelForTesting
    //
    @Before
    fun setUp(){
        viewModel = DeviceViewModelForTesting(FakeDeviceRepository())
    }


    @Test
    fun `empty fieldreturns error`(){

        val device1 = Device(
            1,
            "1600 * 800 ",
            "Android 9",
            2000,
            "USD",
            false,
            R.drawable.samsung,
            "Galaxy A10",
            "Available"
        )

        runBlockingTest {
            viewModel.insert(device1)
        }


        val value = viewModel.insertDeviceItemStatus.getOrAwaitValueTest ()
      //  assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        //assertThat(viewModel.devicesFromLocalDb)


    }

}
