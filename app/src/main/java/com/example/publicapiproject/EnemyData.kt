package com.example.publicapiproject

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EnemyData(
    val id: String?,
    val name: String?,
    val description: String?,
    val region: String?,
    val type: String?,
    val family: String?,
    val faction: String?,
    val elements: List<String>?,
    val drops: List<Drops>?,
    val artifacts: List<Artifacts>,
    @SerializedName("elemental-description")
    val elementalDescription: List<ElementalDescription>,
    @SerializedName("mora-gained")
    val moraGained: Int?
) : Parcelable {
    @Parcelize
    data class Drops(
        val name: String?,
        val rarity: Int?,
        @SerializedName("minimum-level")
        val minimumLevel: Int?
    ) : Parcelable
    @Parcelize
    data class Artifacts(
        val name: String?,
        val set: String?,
        val rarity: String?
    ) : Parcelable
    @Parcelize
    data class ElementalDescription(
        val element: String?,
        val description: String?
    ) : Parcelable
}
