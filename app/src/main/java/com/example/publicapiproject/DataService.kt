package com.example.publicapiproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    // functions to retrieve data from endpoints

    @GET(".")
    fun getMainCategories(
    ) : Call<Map<String, List<String>>>

    // for artifacts, characters, domains, elements, & enemies
    @GET("{list}")
    fun getListOfNamesByType(
        @Path("list") list: String
    ) : Call<List<String>>

    @GET("artifacts/{name}")
    fun getArtifactDataByName(
        @Path("name") name: String
    ) : Call<ArtifactData>

    @GET("characters/{name}")
    fun getCharacterDataByName(
        @Path("name") name: String
    ) : Call<CharacterData>

    @GET("consumables/food")
    fun getConsumablesFood(
    ) : Call<Map<String, ConsumablesFoodData>>

    @GET("consumables/potions")
    fun getConsumablesPotions(
    ) : Call<Map<String, ConsumablesPotionsData>>

    @GET("domains/{name}")
    fun getDomainDataByName(
        @Path("name") name: String
    ) : Call<DomainData>

    @GET("elements/{name}")
    fun getElementDataByName(
        @Path("name") name: String
    ) : Call<ElementData>

    @GET("enemies/{name}")
    fun getEnemyDataByName(
        @Path("name") name: String
    ) : Call<EnemyData>

    @GET("materials/boss-material")
    fun getBossMaterials(
    ) : Call<Map<String, MaterialBossData>>

    @GET("materials/character-ascension")
    fun getCharacterAscensionMaterials(
    ) : Call<Map<String, Map<String, MaterialCharacterAscensionData>>>

    @GET("materials/character-experience")
    fun getCharacterExperienceMaterials(
    ) : Call<Map<String, MaterialCharacterExperienceData>>

    @GET("materials/common-ascension")
    fun getCommonAscensionMaterials(
    ) : Call<Map<String, MaterialCommonAscensionData>>

    @GET("materials/cooking-ingredients")
    fun getCookingIngredients(
    ) : Call<Map<String, MaterialCookingIngredientData>>

    @GET("materials/local-specialties")
    fun getLocalSpecialties(
    ) : Call<Map<String, MaterialLocalSpecialtyData>>

    @GET("materials/talent-book")
    fun getTalentBookMaterials(
    ) : Call<Map<String, MaterialTalentBookData>>

    @GET("materials/weapon-ascension")
    fun getWeaponAscensionMaterials(
    ) : Call<Map<String, MaterialWeaponAscensionData>>

    @GET("materials/weapon-experience")
    fun getWeaponExperienceMaterial(
    ) : Call<Map<String, MaterialWeaponExperienceData>>

    @GET("nations/{name}")
    fun getNationDataByName(
        @Path("name") name: String
    ) : Call<NationData>

    @GET("weapons/{name}")
    fun getWeaponDataByName(
        @Path("name") name: String
    ) : Call<WeaponData>
}