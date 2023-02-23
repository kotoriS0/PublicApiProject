package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NationData(
    val name: String?,
    val element: String?,
    val archon: String?,
    val controllingEntity: String?
) : Parcelable
