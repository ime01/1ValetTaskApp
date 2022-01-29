package com.example.a1valettaskapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a1valettaskapp.data.repository.DevicesRepositoryImpl
import com.example.a1valettaskapp.data.roomdb.DefaultDataSource
import com.example.a1valettaskapp.data.roomdb.DeviceDao
import com.example.a1valettaskapp.data.roomdb.DeviceDatabase
import com.example.a1valettaskapp.domain.repository.DeviceRepository
import com.example.a1valettaskapp.common.Constants.DATABASENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   //this DaggerHilt Module provides the database and dao used to provide devices data, also can be used for
    // edit, update and deletion of a device in the future

    @Provides
    @Singleton
    fun providesDeviceDatabase(@ApplicationContext app: Context,  devicesDaoProvider: Provider<DeviceDao>) =
        Room.databaseBuilder(app, DeviceDatabase::class.java, DATABASENAME) .addCallback(object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val deviceList = DefaultDataSource().ListOfDevices()

                CoroutineScope(Dispatchers.IO).launch {
                    devicesDaoProvider.get().insertDeviceList(deviceList)
                }


            }

        }).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDevicesDao(db : DeviceDatabase) = db.deviceDao()


    //This provides our repository implementation which will give us data
    @Provides
    @Singleton
    fun providesDevicesRepository(deviceDao: DeviceDao): DeviceRepository{
        return DevicesRepositoryImpl(deviceDao)
    }





}