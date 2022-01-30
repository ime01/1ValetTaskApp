package com.example.a1valettaskapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import coil.load
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.databinding.FragmentDetailBinding
import com.example.a1valettaskapp.databinding.FragmentHomeBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var device: Device? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            device =  DetailFragmentArgs.fromBundle(it).device
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)


        binding.apply {

            device?.apply {

                deviceName.text = "Name - ${name}"
                deviceOs.text = "OS - ${os}"
                deviceAvailabity.text = "Status - ${status}"
                devicePrice.text = " Price -   ${price} ${currency}"
                deviceSize.text = "Size - ${size}"

            }

            device?.imageUrl?.let {
                deviceIcon.load(it){
                    error(R.drawable.ic_baseline_not_interested_24)
                    placeholder(R.drawable.ic_baseline_phone_android_24)
                    crossfade(true)
                    crossfade(1000)
                }
            }

        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}