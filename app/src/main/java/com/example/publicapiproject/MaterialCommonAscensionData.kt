package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialCommonAscensionData(
    val characters: List<String>,
    val items: List<Item>,
    val sources: List<String>
) : Parcelable {
    @Parcelize
    data class Item(
        val id: String?,
        val name: String?,
        val rarity: Int?
    ) : Parcelable
}
