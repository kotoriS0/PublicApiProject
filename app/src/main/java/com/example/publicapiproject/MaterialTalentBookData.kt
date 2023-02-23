package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialTalentBookData(
    val characters: List<String>,
    val availability: List<String>,
    val source: String?,
    val items: List<Item>
) : Parcelable {
    @Parcelize
    data class Item(
        val id: String?,
        val name: String?,
        val rarity: Int?
    ) : Parcelable
}
