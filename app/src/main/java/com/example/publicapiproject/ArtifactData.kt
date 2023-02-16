package com.example.publicapiproject

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtifactData(
    val name: String?,
    @SerializedName("max_rarity")
    val maxRarity: Int?,
    @SerializedName("2-piece_bonus")
    val twoPieceBonus: String?,
    @SerializedName("4-piece_bonus")
    val fourPieceBonus: String?
) : Parcelable
