package com.example.a1valettaskapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.a1valettaskapp.data.model.Device

class DevicesDiffCallback : DiffUtil.ItemCallback<Device>(){


    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }

}