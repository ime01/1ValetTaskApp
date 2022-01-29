package com.example.a1valettaskapp.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.adapter.DeviceAdapter
import com.example.a1valettaskapp.common.Constants.NIGHTORDAY
import com.example.a1valettaskapp.common.onQueryTextChanged
import com.example.a1valettaskapp.data.model.Device
import com.example.a1valettaskapp.databinding.FragmentHomeBinding
import com.example.a1valettaskapp.presentation.DeviceViewModel
import com.google.android.material.snackbar.Snackbar
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

        loadSettings()
        showWelcomeMarqueeText()
        loadData()
        deviceadapter = DeviceAdapter{
            transitionToDetailView(it)
        }
        swipeToDeleteNumber()


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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_layout, menu)
        val menuItem = menu!!.findItem(R.id.search_device)
        val searchView = menuItem.actionView as SearchView

        searchView.onQueryTextChanged {
            searchDatabase(it)
        }
    }

    private fun searchDatabase(query:String){
        val searchQuery = "%$query%"
        deviceviewModel.searchDevice(searchQuery).observe(viewLifecycleOwner, Observer {list->
            list.let {
                deviceadapter.submitList(it)
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.search_device -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
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



    private fun swipeToDeleteNumber() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val device = deviceadapter.currentList[viewHolder.adapterPosition]

                deviceviewModel.deleteDevice(device )
                Snackbar.make(binding.rvDevices, " ${device.name} Device Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO"){
                        deviceviewModel.insertDevice(device)
                    }.show()
            }

        }).attachToRecyclerView(binding.rvDevices)
    }

    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val nightMode = sp.getBoolean(NIGHTORDAY, false)

        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}