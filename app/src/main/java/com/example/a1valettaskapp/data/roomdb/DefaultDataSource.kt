package com.example.a1valettaskapp.data.roomdb

import android.content.Context
import android.net.Uri
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.data.model.Device


//Writing this datasource we will pass to our RoomDatabase to Populate our recyclerview with Devices data to mimick,
// making a network call and fetching this data

class DefaultDataSource {


    fun ListOfDevices(): List<Device> {
        val devList: ArrayList<Device> = ArrayList()

        devList.apply {
            add(0, Device(1,
                "1600 * 800 ",
                "Android 9",
                2000,
                "USD",
                false,
                R.drawable.samsung,
                "Galaxy A10",
                "Available"
            ),)

            add(1, Device(2,
                "1600 * 800 ",
                "Android 8",
                1500,
                "USD",
                false,
                R.drawable.samsunggalaxya5,
                "Galaxy A5",
                "Available"
            ),)

            add(2, Device(3,
                "1600 * 800 ",
                "Android 8",
                1950,
                "USD",
                false,
                R.drawable.samsunga15,
                "Galaxy A15",
                "Available"
            ),)

            add(3, Device(4,
                "1600 * 800 ",
                "Android 11",
                5000,
                "USD",
                false,
                R.drawable.samsunga19,
                "Galaxy A19",
                "Available"
            ),)

            add(4, Device(5,
                "1600 * 800 ",
                "Android 12",
                7000,
                "USD",
                false,
                R.drawable.samsunga52,
                "Galaxy A52",
                "Available"
            ),)

            add(5, Device(6,
                "1600 * 800 ",
                "Android 7",
                3000,
                "USD",
                false,
                R.drawable.samsunga32,
                "Galaxy A32",
                "Available"
            ),)

            add(6, Device(7,
                "1600 * 800 ",
                "Android 11",
                3500,
                "USD",
                false,
                R.drawable.motorlla,
                "Galaxy A32",
                "Available"
            ),)

            add(7, Device(8,
                "1600 * 800 ",
                "Android 8",
                4500,
                "USD",
                false,
                R.drawable.oddo,
                "Oddo Smartphone",
                "Available"
            ),)

            add(8, Device(9,
                "1600 * 800 ",
                "Ios",
                10000,
                "USD",
                false,
                R.drawable.iphone,
                "Iphone",
                "Available"
            ),)

            add(9, Device(10,
                "1600 * 800 ",
                "Android 12",
                13000,
                "USD",
                false,
                R.drawable.samsunga15,
                "Iphone",
                "Available"
            ),)



            add(10, Device(11,
                "1600 * 800 ",
                "Android 6",
                6000,
                "USD",
                false,
                R.drawable.samsunga19,
                "Google pixel 6a",
                "Available"
            ),)

            add(11, Device(12,
                "1600 * 800 ",
                "Android 8",
                14500,
                "USD",
                false,
                R.drawable.samsunga52,
                "Samsung A4",
                "Available"
            ),)

            add(12, Device(13,
                "1600 * 800 ",
                "Android 7",
                11000,
                "USD",
                false,
                R.drawable.samsung,
                "Samsung A5",
                "Available"
            ),)

            add(13, Device(14,
                "1600 * 800 ",
                "Android 5",
                16000,
                "USD",
                false,
                R.drawable.samsunggalaxya5,
                "Google pixel 3a",
                "Available"
            ),)

            add(14, Device(15,
                "1600 * 800 ",
                "Android 6",
                19000,
                "USD",
                false,
                R.drawable.samsunga52,
                "Google pixel 5a",
                "Available"
            ),)
        }
        return devList


    }


}