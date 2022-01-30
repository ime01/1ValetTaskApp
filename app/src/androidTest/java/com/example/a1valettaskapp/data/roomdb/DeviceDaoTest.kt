package com.example.a1valettaskapp.data.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.data.model.Device
import com.flowz.drinkcocktails.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DeviceDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DeviceDatabase
    private lateinit var dao: DeviceDao

    @Before
    fun Setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), DeviceDatabase::class.java).allowMainThreadQueries().build()
        dao = database.deviceDao()
    }

    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertDevice() = runBlockingTest{
        val device = Device(
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

        dao.insert(device)

        val allDevices = dao.getDevicesLiveDataType().getOrAwaitValue()

        assertThat(allDevices).contains(device)
    }


    @Test
    fun deleteDrink() = runBlockingTest{
        val device = Device(
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

        dao.delete(device)

        val allDevices = dao.getDevicesLiveDataType().getOrAwaitValue()

        assertThat(allDevices).doesNotContain(device)
    }

}