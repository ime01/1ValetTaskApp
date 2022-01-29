package com.example.a1valettaskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.databinding.DeviceListItemBinding



typealias urlListener = (item: Device) -> Unit


class DeviceAdapter  (val listener: urlListener)  :ListAdapter<Device, DeviceAdapter.ImageViewHolder>(DevicesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_list_item, parent, false)

        return ImageViewHolder(DeviceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            getItem(it)?.let{item-> listener(item)}
        }

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                deviceName.text = "${currentItem.name}"
                deviceAvalabilty.text = "Status: ${currentItem.status}"

                val imageLink = currentItem?.imageUrl

               deviceIcon.load(imageLink!!){
                    error(R.drawable.ic_baseline_not_interested_24)
                    placeholder(R.drawable.ic_baseline_phone_android_24)
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    inner class ImageViewHolder(val binding: DeviceListItemBinding, private val listener: (Int)-> Unit): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }


   /* interface RowClickListener{
        fun onItemClickListener(car: Device)

    }*/

}