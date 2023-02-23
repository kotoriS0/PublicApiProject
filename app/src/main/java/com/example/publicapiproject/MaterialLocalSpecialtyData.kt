package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialLocalSpecialtyData(
    val id: String?,
    val name: String?,
    val characters: List<String>?
) : Parcelable
