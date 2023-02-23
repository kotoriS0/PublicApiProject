package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialWeaponExperienceData(
    val id: String?,
    val name: String?,
    val experience: Int?,
    val rarity: Int?,
    val source: List<String>
) : Parcelable
