package com.mgt.scorefootballapi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mgt.scorefootballapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()

        setupNavController()
        setupBottomNavigation()
    }

    private fun setupViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}