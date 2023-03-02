package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConsumablesFoodData(
    val name: String?,
    val rarity: Int?,
    val type: String?,
    val effect: String?,
    val hasRecipe: Boolean?,
    val proficiency: Int?,
    val description: String?,
    val recipe: List<Recipe>?
) : Parcelable {
    @Parcelize
    data class Recipe(
        val item: String?,
        val quantity: Int?
    ) : Parcelable
}
