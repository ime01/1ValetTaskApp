package com.example.a1valettaskapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.databinding.FragmentHomeBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}