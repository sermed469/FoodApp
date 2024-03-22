package com.sermedkerim.bitirmeprojesi

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationBarView
import com.sermedkerim.bitirmeprojesi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.navController)
        binding.bottomNavigationView.itemActiveIndicatorColor =
            ColorStateList.valueOf(resources.getColor(R.color.activeIndicatorColor))

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.cartFragment)
        badge.backgroundColor = resources.getColor(R.color.colorSecondary)

        val model = ViewModelProvider(this).get(SharedViewModel::class.java)

        model.numberofFoods.observe(this){
            Log.e("Badge",it.toString())
            if(it == 0){
                badge.isVisible = false
                badge.clearNumber()
            }else{
                badge.isVisible = true
                badge.number = it
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainPageFragment -> {
                    navHostFragment.findNavController().navigate(R.id.mainPageFragment)
                    true
                }

                R.id.favouriteFragment -> {
                    navHostFragment.findNavController().navigate(R.id.favouriteFragment)
                    true
                }

                R.id.cartFragment -> {
                    navHostFragment.findNavController().navigate(R.id.cartFragment)
                    true
                }

                else -> false
            }
        }

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.mainPageFragment -> {

                }

                R.id.favouriteFragment -> {

                }

                R.id.cartFragment -> {

                }
            }
        }

        navHostFragment.findNavController().addOnDestinationChangedListener{ controller,destination,bundle ->
            if(destination.id == R.id.detailFragment){
                binding.bottomNavigationView.visibility = View.GONE
            }else{
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}