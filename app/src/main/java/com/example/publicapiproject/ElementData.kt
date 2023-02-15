package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ElementData(
    val name: String?,
    val key: String?,
    val reactions: List<Reaction>
) : Parcelable {
    @Parcelize
    data class Reaction(
        val name: String?,
        val elements: List<String>,
        val description: String?
    ) : Parcelable
}
