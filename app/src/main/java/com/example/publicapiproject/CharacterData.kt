package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    val name: String?,
    val title: String?,
    val vision: String?,
    val weapon: String?,
    val nation: String?,
    val affiliation: String?,
    val rarity: Int?,
    val constellation: String?,
    val birthday: String?,
    val description: String?,
    val skillTalents: SkillTalents,
    val passiveTalents: PassiveTalents,
    val constellations: Constillations
) : Parcelable {
    @Parcelize
    data class SkillTalents (
        val name: String?,
        val unlock: String?,
        val description: String?,
        val upgrades: Upgrades
    ) : Parcelable

    @Parcelize
    data class Upgrades (
        val name: String?,
        val value: String?
    ) : Parcelable

    @Parcelize
    data class PassiveTalents (
        val name: String?,
        val unlock: String?,
        val description: String?
    ) : Parcelable

    @Parcelize
    data class Constillations (
        val name: String?,
        val unlock: String?,
        val description: String?,
        val level: Int?
    ) : Parcelable
}
