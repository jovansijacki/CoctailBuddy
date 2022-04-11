package com.example.coctailbuddy.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.coctailbuddy.R
import com.example.coctailbuddy.activity.MainActivity
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.room.LocalStorageRepository
import com.example.coctailbuddy.viewmodels.FavoritesDetailsViewModel

class FavoritesDetailsFragment : Fragment(R.layout.favorites_details_fragment) {
    private lateinit var viewModel: FavoritesDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val coctail = requireArguments().getParcelable<CoctailItem>("CoctailItem")

        showCoctail(coctail)

        val deleteButton = requireView().findViewById<ImageButton>(R.id.deleteButton)
        val repository = LocalStorageRepository((activity as MainActivity).database)

        viewModel = ViewModelProvider(this)[FavoritesDetailsViewModel::class.java]
        viewModel.setRepo(repository)

        deleteButton.setOnClickListener {
            viewModel.deleteItem(coctail!!.idDrink)
        }
    }

    private fun showCoctail(item: CoctailItem?) {

        val view = this.requireView()
        val imgView = view.findViewById<ImageView>(R.id.favortes_details_IV)
        val name = view.findViewById<TextView>(R.id.favortes_details_name)
        val strAlcoholic = view.findViewById<TextView>(R.id.favortes_strAlcoholic)
        val strCategory = view.findViewById<TextView>(R.id.favortes_strCategory)
        val strGlass = view.findViewById<TextView>(R.id.favortes_strGlass)
        val strIngredients = view.findViewById<TextView>(R.id.favortes_strIngredients)
        val strInstructions = view.findViewById<TextView>(R.id.favortes_strInstructions)

        Glide.with(this)
            .load(item!!.strDrinkThumb)
            .into(imgView)

        name.text = item.strDrink
        strAlcoholic.text = item.strAlcoholic
        strCategory.text = item.strCategory
        strGlass.text = item.strGlass
        strIngredients.text = item.getIngredients()
        strInstructions.text = item.strInstructions
    }
}