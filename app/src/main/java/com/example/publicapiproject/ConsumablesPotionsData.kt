package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConsumablesPotionsData(
    val name: String?,
    val effect: String?,
    val rarity: Int?,
    val crafting: List<Crafting>?,
) : Parcelable {
    @Parcelize
    data class Crafting (
        val item: String?,
        val quantity: Int?
    ) : Parcelable
}
