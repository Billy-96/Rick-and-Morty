package com.example.rick_and_mortywiki.Interfaces

import com.example.rick_and_mortywiki.Model.DataClasses.*
import retrofit2.Response
import java.lang.Character

interface RepoImp {
    suspend fun getCharactersFromNet(
        page:Int,name: String, status: String, species: String, type: String, gender: String): Response<CharacterNet>

    suspend fun getLocationsFromNet(page:Int,name: String, type: String, dimension: String): Response<LocationNet>

    suspend fun getEpisodesFromNet(page: Int,name: String, episode: String): Response<EpisodeNet>
    suspend fun getOneEpisode(id: String):Response<Episode>

    suspend fun findEpisode(name: String): Response<EpisodeNet>
    suspend fun findCharacter( name: String, gender: String, status: String): Response<CharacterNet>
    suspend fun findLocation( name: String, type: String, dimension: String): Response<LocationNet>
}