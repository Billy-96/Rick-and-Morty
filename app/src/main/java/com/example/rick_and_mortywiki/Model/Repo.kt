package com.example.rick_and_mortywiki.Model

import com.example.rick_and_mortywiki.Interfaces.RepoImp
import com.example.rick_and_mortywiki.Model.DataClasses.CharacterNet
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.EpisodeNet
import com.example.rick_and_mortywiki.Model.DataClasses.LocationNet
import com.example.rick_and_mortywiki.Network.NetworkUtils
import retrofit2.Response

class Repo:RepoImp {
    override suspend fun getCharactersFromNet(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): Response<CharacterNet> {
        return NetworkUtils.getIntance().getCharacterFromNet(name,status,species,type,gender)
    }

    override suspend fun getLocationsFromNet(
        name: String,
        type: String,
        dimension: String
    ): Response<LocationNet> {
        return NetworkUtils.getIntance().getLocationFromNet(name,type,dimension)
    }

    override suspend fun getEpisodesFromNet(
        name: String,
        episode: String
    ): Response<EpisodeNet> {
        return NetworkUtils.getIntance().getEpisodeFromNet(name,episode)
    }

    override suspend fun getOneEpisode(id: String): Response<Episode> {
        return NetworkUtils.getIntance().getOneEpisode(id)
    }
}