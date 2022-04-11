package com.example.coctailbuddy.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.coctailbuddy.R
import com.example.coctailbuddy.activity.MainActivity
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.data.SmallCoctailItem
import com.example.coctailbuddy.room.LocalStorageRepository
import com.example.coctailbuddy.viewmodels.DetailsFragmentViewModel

class DetailsFragment : Fragment(R.layout.coctail_details_fragment) {
    private lateinit var viewModel: DetailsFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = LocalStorageRepository((activity as MainActivity).database)
        val saveButton = requireView().findViewById<Button>(R.id.saveButton)
        var fetchedItem: CoctailItem? = null

        postponeEnterTransition()

        viewModel = ViewModelProvider(this)[DetailsFragmentViewModel::class.java]
        viewModel.setLocalDataRepo(repository)

        var coctailID: String? = null
        if (arguments != null) {
            coctailID =
                requireArguments().getParcelable<SmallCoctailItem>("SmallCoctailItem")?.idDrink
        }

        viewModel.getCoctailDetail().observe(viewLifecycleOwner) {
            fetchedItem = it
            requireActivity().title = it.strDrink
            setDetailsToViews(fetchedItem)

            startPostponedEnterTransition()
        }

        if (coctailID != null) {
            viewModel.getCoctailDetailsbyID(coctailID)
        }
        saveButton.setOnClickListener {
            viewModel.saveToLocalDatabase(fetchedItem)
        }
    }

    private fun setDetailsToViews(it: CoctailItem?) {

        val view = this.requireView()
        val imgView = view.findViewById<ImageView>(R.id.details_IV)
        val name = view.findViewById<TextView>(R.id.details_name)
        val strAlcoholic = view.findViewById<TextView>(R.id.strAlcoholic)
        val strCategory = view.findViewById<TextView>(R.id.strCategory)
        val strGlass = view.findViewById<TextView>(R.id.strGlass)
        val strIngredients = view.findViewById<TextView>(R.id.strIngredients)
        val strInstructions = view.findViewById<TextView>(R.id.strInstructions)

        Glide.with(this)
            .load(it!!.strDrinkThumb)
            .into(imgView)

        name.text = it.strDrink
        strAlcoholic.text = it.strAlcoholic
        strCategory.text = it.strCategory
        strGlass.text = it.strGlass
        strIngredients.text = it.getIngredients()
        strInstructions.text = it.strInstructions
    }
}