package com.example.publicapiproject

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainData(
    val name: String?,
    val type: String?,
    val description: String?,
    val location: String?,
    val nation: String?,
    val recommendedElements: List<String>?,
    val requirements: List<Requirements>?,
    val rewards: List<Rewards>?
) : Parcelable {
    @Parcelize
    data class Requirements(
        val level: Int?,
        val adventureRank: Int?,
        val recommendedLevel: Int?,
        val leyLineDisorder: List<String?>
    ) : Parcelable
    @Parcelize
    data class Rewards(
        val day: String?,
        val details: List<Details>
    ) : Parcelable
    @Parcelize
    data class Details(
        val level: Int?,
        val adventureExperience: Int?,
        val companionshipExperience: Int?,
        val mora: Int?,
        val drops: List<Drops>?
    ) : Parcelable
    @Parcelize
    data class Drops(
        val name: String?,
        val rarity: String?,
        @SerializedName("drop_min")
        val dropMin: Int?,
        @SerializedName("drop_max")
        val dropMax: Int?

    ) : Parcelable
}
