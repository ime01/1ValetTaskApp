package com.example.a1valettaskapp.ui.home

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.adapter.DeviceAdapter
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.databinding.FragmentHomeBinding
import com.example.a1valettaskapp.presentation.DeviceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var deviceadapter: DeviceAdapter

    private val deviceviewModel by viewModels<DeviceViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.shimmerFrameLayout.startShimmerAnimation()

        showWelcomeMarqueeText()
        loadData()
        deviceadapter = DeviceAdapter{
            transitionToDetailView(it)
        }


    }

    fun loadData(){

        lifecycleScope.launchWhenStarted {

            //Simulating a network call with a 2 seconds delay to get the data
            delay(2000)
            deviceviewModel.devicesFromSimulatedNetworkCall.collect {
                loadRecyclerView(it)
            }
        }

    }

    fun loadRecyclerView(devices: List<Device>) {
        deviceadapter.submitList(devices)
        binding.apply {

            rvDevices.layoutManager = LinearLayoutManager(requireContext())
            rvDevices.adapter = deviceadapter
            val decoration = DividerItemDecoration(context, VERTICAL)
            rvDevices.addItemDecoration(decoration)

            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE

        }

    }

    private fun transitionToDetailView(device: Device) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        action.device = device
        Navigation.findNavController(requireView()).navigate(action)
    }


    fun showWelcomeMarqueeText() {
        binding.welcomeTextMarquee.apply {
            setSingleLine()
            ellipsize = TextUtils.TruncateAt.MARQUEE
            marqueeRepeatLimit = -1
            isSelected = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}