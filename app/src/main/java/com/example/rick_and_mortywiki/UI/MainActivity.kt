package com.example.rick_and_mortywiki.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rick_and_mortywiki.R
import com.example.rick_and_mortywiki.UI.Fragments.EpisodesFragment
import com.example.rick_and_mortywiki.UI.Fragments.LocationsFragment
import com.example.rick_and_mortywiki.UI.Fragments.PersonsFragment
import com.example.rick_and_mortywiki.UI.Fragments.SearchFragment
import com.example.rick_and_mortywiki.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

    }
}