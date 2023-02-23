package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeaponData(
    val name: String?,
    val type: String?,
    val rarity: Int?,
    val baseAttack: Int?,
    val subStat: String?,
    val passiveName: String?,
    val passiveDesc: String?,
    val location: String?,
    val ascensionMaterial: String?
) : Parcelable
