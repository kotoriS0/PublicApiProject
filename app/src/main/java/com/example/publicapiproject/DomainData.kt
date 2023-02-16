package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainData(
    val name: String?,
    val type: String?,
    val description: String?,
    val location: String?,
    val nation: String?,
    val requirements: List<Requirements>
) : Parcelable {
    @Parcelize
    data class Requirements(
        val level: Int?,
        val adventureRank: Int?,
        val recommendedLevel: Int?,
        val leyLineDisorder: List<String?>
    ) : Parcelable
}
