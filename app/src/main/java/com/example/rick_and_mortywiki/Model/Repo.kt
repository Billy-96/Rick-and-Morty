package com.example.rick_and_mortywiki.Model

import com.example.rick_and_mortywiki.Interfaces.RepoImp
import com.example.rick_and_mortywiki.Model.DataClasses.*
import com.example.rick_and_mortywiki.Network.NetworkUtils
import retrofit2.Response
import java.lang.Character

class Repo : RepoImp {
    override suspend fun getCharactersFromNet(
        page: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): Response<CharacterNet> {
        return NetworkUtils.getIntance()
            .getCharacterFromNet(page, name, status, species, type, gender)
    }

    override suspend fun getLocationsFromNet(
        page: Int,
        name: String,
        type: String,
        dimension: String
    ): Response<LocationNet> {
        return NetworkUtils.getIntance().getLocationFromNet(page, name, type, dimension)
    }

    override suspend fun getEpisodesFromNet(
        page: Int,
        name: String,
        episode: String
    ): Response<EpisodeNet> {
        return NetworkUtils.getIntance().getEpisodeFromNet(page, name, episode)
    }

    override suspend fun getOneEpisode(id: String): Response<Episode> {
        return NetworkUtils.getIntance().getOneEpisode(id)
    }

    override suspend fun findEpisode(name: String): Response<EpisodeNet> {
        return NetworkUtils.getIntance().findEpisodes(name)
    }

    override suspend fun findCharacter(
        name: String,
        gender: String,
        status: String
    ): Response<CharacterNet> {
        return NetworkUtils.getIntance().findCharacter(name, gender, status)
    }

    override suspend fun findLocation(
        name: String,
        type: String,
        dimension: String
    ): Response<LocationNet> {
        return NetworkUtils.getIntance().findLocations(name, type, dimension)
    }


}