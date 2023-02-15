package com.example.publicapiproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataService {
    // functions to retrieve data from endpoints

    @GET("artifacts/{name}")
    fun getArtifactDataByName(
        @Path("name") name: String
    ) : Call<ArtifactData>

    @GET("characters/{name}")
    fun getCharacterDataByName(
        @Path("name") name: String
    ) : Call<CharacterData>


}