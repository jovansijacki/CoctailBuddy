package com.example.coctailbuddy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COCTAILDATABASE")
data class CoctailItem(
    val dateModified: String?,
    @PrimaryKey
    val idDrink: String,
    val strAlcoholic: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: String?,
    val strDrink: String?,
    val strDrinkAlternate: String?,
    val strDrinkThumb: String,
    val strGlass: String?,
    val strIBA: String?,
    val strImageAttribution: String?,
    val strImageSource: String?,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String? ,
    val strMeasure13: String? ,
    val strMeasure14: String? ,
    val strMeasure15: String?,
    val strMeasure2: String? ,
    val strMeasure3: String? ,
    val strMeasure4: String? ,
    val strMeasure5: String? ,
    val strMeasure6: String? ,
    val strMeasure7: String? ,
    val strMeasure8: String? ,
    val strMeasure9: String? ,
    val strTags: String?,
) {
    fun getIngredients(): String {
        return "${strIngredient1?:""}  ${strMeasure1?:""}\n" +
                "${strIngredient2?:""}  ${strMeasure2?:""}\n" +
                "${strIngredient3?:""}  ${strMeasure3?:""}\n" +
                "${strIngredient4?:""}  ${strMeasure4?:""}\n" +
                "${strIngredient5?:""}  ${strMeasure5?:""}\n" +
                "${strIngredient6?:""}  ${strMeasure6?:""}\n" +
                "${strIngredient7?:""}  ${strMeasure7?:""}\n" +
                "${strIngredient8?:""}  ${strMeasure8?:""}\n" +
                "${strIngredient9?:""}  ${strMeasure9?:""}\n" +
                "${strIngredient10?:""}  ${strMeasure10?:""}\n" +
                "${strIngredient11?:""}  ${strMeasure11?:""}\n" +
                "${strIngredient12?:""}  ${strMeasure12?:""}\n" +
                "${strIngredient13?:""}  ${strMeasure13?:""}\n" +
                "${strIngredient14?:""}  ${strMeasure14?:""}\n" +
                "${strIngredient15?:""}  ${strMeasure15?:""}\n"



    }
}