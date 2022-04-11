package com.example.coctailbuddy.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coctailbuddy.viewmodels.HomeFragmentViewModel
import com.example.coctailbuddy.R
import com.example.coctailbuddy.activity.MainActivity
import com.example.coctailbuddy.data.SmallCoctailItem

class HomeFragment : Fragment(R.layout.home_fragment), HomeFragmentAdapterOnClickListener {
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "All coctails"

        val recyclerView = view.findViewById<RecyclerView>(R.id.home_fragment_RV)
        val myAdapter = HomeFragmentAdapter()
        myAdapter.setClickListener(this)
        recyclerView.adapter = myAdapter
        val linearManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearManager

        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        viewModel.getCoctails().observe(viewLifecycleOwner) {
            myAdapter.setDataset(it)
        }
        viewModel.getAllCoctails()
    }

    override fun onClick(item: SmallCoctailItem) {
        (activity as MainActivity).launchDetailsFragment(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (activity as MainActivity).launchFavoritesFragment()
        return super.onOptionsItemSelected(item)
    }
}

interface HomeFragmentAdapterOnClickListener {
    fun onClick(item: SmallCoctailItem)
}