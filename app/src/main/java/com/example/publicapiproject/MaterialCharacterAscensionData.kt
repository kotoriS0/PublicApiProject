package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//add wrapper? thing
@Parcelize
data class MaterialCharacterAscensionData(
    val id: String?,
    val name: String?,
    val sources: List<String>,
    val rarity: Int?
) : Parcelable
