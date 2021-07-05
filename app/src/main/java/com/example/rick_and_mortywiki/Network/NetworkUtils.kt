package com.example.rick_and_mortywiki.Network

import com.example.rick_and_mortywiki.Model.DataClasses.*
import com.example.rick_and_mortywiki.Utils.Util
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkUtils {

    @GET("character")
    suspend fun getCharacterFromNet(
        @Query("name") name:String,
        @Query("status") status:String,
        @Query("species") species:String,
        @Query("type") type:String,
        @Query("gender") gender:String
    ): Response<CharacterNet>

    @GET("episode/{id}")
    suspend fun getOneEpisode(
        @Path("id") id:String
    ):Response<Episode>

    @GET("location")
    suspend fun getLocationFromNet(
        @Query("name") name:String,
        @Query("type") type:String,
        @Query("dimension") dimension:String
    ): Response<LocationNet>

    @GET("episode")
    suspend fun getEpisodeFromNet(
        @Query("name") name:String,
        @Query("episode") episode:String
    ): Response<EpisodeNet>

    companion object {
        private var retrofitService: NetworkUtils? = null
        fun getIntance(): NetworkUtils {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Util.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(NetworkUtils::class.java)
            }
            return retrofitService!!
        }
    }
}