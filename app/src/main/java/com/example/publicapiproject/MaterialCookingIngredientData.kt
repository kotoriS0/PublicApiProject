package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialCookingIngredientData(
    val name: String?,
    val description: String?,
    val sources: List<String>?,
) : Parcelable
