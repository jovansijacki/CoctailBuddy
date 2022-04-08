package com.example.coctailbuddy.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coctailbuddy.R
import com.example.coctailbuddy.activity.MainActivity
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.room.LocalStorageRepository
import com.example.coctailbuddy.viewmodels.FavoritesFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(R.layout.favorites_fragment),
    FavoritesFragmentAdapterOnClickListener {
    private lateinit var viewModel: FavoritesFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Favorite Coctails"

        val repository = LocalStorageRepository((activity as MainActivity).database)
        val recyclerView = view.findViewById<RecyclerView>(R.id.favorites_fragment_RV)
        val myAdapter = FavoritesFragmentAdapter()
        val linearManager = LinearLayoutManager(context)

        setHasOptionsMenu(true)

        myAdapter.setClickListener(this)
        recyclerView.adapter = myAdapter

        recyclerView.layoutManager = linearManager

        viewModel = ViewModelProvider(this)[FavoritesFragmentViewModel::class.java]
        viewModel.setLocalDataRepo(repository)

        var favoriteCoctails: List<CoctailItem>?

        CoroutineScope(Dispatchers.Main).launch {
            favoriteCoctails = viewModel.getAllCoctailsFromLocalDatabase().await()
            showFavorites(favoriteCoctails!!.size)
            myAdapter.setDataset(ArrayList(favoriteCoctails!!))
            showIfNoFavorites(favoriteCoctails!!.size)
        }
    }

    private fun showFavorites(size: Int) {
        if (size > 0) {
            requireView().findViewById<RecyclerView>(R.id.favorites_fragment_RV).visibility =
                View.VISIBLE
            requireView().findViewById<TextView>(R.id.error_text).visibility =
                View.INVISIBLE
        }
    }

    private fun showIfNoFavorites(size: Int) {
        if (size == 0) {
            requireView().findViewById<RecyclerView>(R.id.favorites_fragment_RV).visibility =
                View.INVISIBLE
            requireView().findViewById<TextView>(R.id.error_text).visibility =
                View.VISIBLE
        }
    }

    override fun onClick(item: CoctailItem) {
        (activity as MainActivity).launchFavoritesDetailsFragment(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.favorites_menu, menu)
    }
}

interface FavoritesFragmentAdapterOnClickListener {
    fun onClick(item: CoctailItem)
}