package com.example.a1valettaskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_1ValetTaskApp)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }

}