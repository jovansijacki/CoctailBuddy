package com.example.coctailbuddy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.coctailbuddy.R
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.data.SmallCoctailItem
import com.example.coctailbuddy.fragment.FavoritesFragmentDirections
import com.example.coctailbuddy.fragment.HomeFragmentDirections
import com.example.coctailbuddy.room.CoctailDatabase
import com.example.coctailbuddy.room.DataBaseInstance

class MainActivity : AppCompatActivity() {
    lateinit var database: CoctailDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = DataBaseInstance.getDatabase(applicationContext)
    }

    fun launchDetailsFragment(item: SmallCoctailItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
        action.arguments.putParcelable("SmallCoctailItem", item)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    fun launchFavoritesFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    fun launchFavoritesDetailsFragment(item: CoctailItem) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesDetailsFragment()
        action.arguments.putParcelable("CoctailItem", item)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
}