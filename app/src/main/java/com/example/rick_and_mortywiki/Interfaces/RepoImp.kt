package com.example.rick_and_mortywiki.Interfaces

import com.example.rick_and_mortywiki.Model.DataClasses.CharacterNet
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.EpisodeNet
import com.example.rick_and_mortywiki.Model.DataClasses.LocationNet
import retrofit2.Response

interface RepoImp {
    suspend fun getCharactersFromNet(
        name: String, status: String, species: String, type: String, gender: String
    ): Response<CharacterNet>

    suspend fun getLocationsFromNet(
        name: String, type: String, dimension: String
    ): Response<LocationNet>

    suspend fun getEpisodesFromNet(
        name: String, episode: String
    ): Response<EpisodeNet>
    suspend fun getOneEpisode(
        id: String
    ):Response<Episode>
}