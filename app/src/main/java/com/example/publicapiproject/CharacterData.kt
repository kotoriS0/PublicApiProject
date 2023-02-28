package com.example.publicapiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    val name: String?,
    val title: String?,
    val vision: String?,
    val weapon: String?,
    val gender: String?,
    val nation: String?,
    val affiliation: String?,
    val specialDish: String?,
    val rarity: Int?,
    val constellation: String?,
    val birthday: String?,
    val description: String?,
    val skillTalents: List<SkillTalent>?,
    val passiveTalents: List<PassiveTalent>?,
    val constellations: List<Constillation>?
) : Parcelable {
    @Parcelize
    data class SkillTalent (
        val name: String?,
        val unlock: String?,
        val description: String?,
        val upgrades: List<Upgrade>?,
        val type: String?
    ) : Parcelable

    @Parcelize
    data class Upgrade (
        val name: String?,
        val value: String?
    ) : Parcelable

    @Parcelize
    data class PassiveTalent (
        val name: String?,
        val unlock: String?,
        val description: String?
    ) : Parcelable

    @Parcelize
    data class Constillation (
        val name: String?,
        val unlock: String?,
        val description: String?,
        val level: Int?
    ) : Parcelable
}
