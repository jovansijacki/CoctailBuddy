package com.example.coctailbuddy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SmallCoctailItem(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
):Parcelable