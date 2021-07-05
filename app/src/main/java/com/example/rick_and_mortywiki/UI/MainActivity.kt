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

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(PersonsFragment())

        val viewModel = MainViewModel()


        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.person -> setFragment(PersonsFragment())
                R.id.location -> setFragment(LocationsFragment())
                R.id.episode -> setFragment(EpisodesFragment())
                R.id.search -> setFragment(SearchFragment())
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}