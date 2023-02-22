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

    @GET("artifacts")
    fun getArtifactNames(
    ) : Call<List<String>>

    @GET("artifacts/{name}")
    fun getArtifactDataByName(
        @Path("name") name: String
    ) : Call<ArtifactData>

    @GET("characters")
    fun getCharacterNames(
    ) : Call<List<String>>

    @GET("characters/{name}")
    fun getCharacterDataByName(
        @Path("name") name: String
    ) : Call<CharacterData>

    @GET("consumables/food")
    fun getConsumablesFood(
    ) : Call<List<ConsumablesFoodData>>

    @GET("consumables/potions")
    fun getConsumablesPotions(
    ) : Call<List<ConsumablesPotionsData>>

    @GET("domains")
    fun getDomainNames(
    ) : Call<List<String>>

    @GET("domains/{name}")
    fun getDomainDataByName(
        @Path("name") name: String
    ) : Call<DomainData>

    @GET("elements")
    fun getElementNames(
    ) : Call<List<String>>

    @GET("elements/{name}")
    fun getElementDataByName(
        @Path("name") name: String
    ) : Call<ElementData>

    @GET("enemies")
    fun getEnemyNames(
    ) : Call<List<String>>

    @GET("enemies/{name}")
    fun getEnemyDataByName(
        @Path("name") name: String
    ) : Call<EnemyData>

    @GET("materials/boss-material")
    fun getBossMaterials(
    ) : Call<List<MaterialBossData>>

    @GET("materials/character-ascension")
    fun getCharacterAscensionMaterials(
    ) : Call<Map<String, Map<String, MaterialCharacterAscensionData>>>
}