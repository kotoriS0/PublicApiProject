package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialBossData(
    val name: String?,
    val source: String?,
    val characters: List<String>?
) : Parcelable
