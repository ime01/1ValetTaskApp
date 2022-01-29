package com.example.a1valettaskapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a1valettaskapp.common.Constants.TABLENAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLENAME)
@Parcelize
data class Device(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val size: String,
    val os : String,
    val price: Int,
    val currency: String,
    val isFavorite: Boolean,
    val imageUrl: Int,
    val name: String,
    val status: String,
) : Parcelable
