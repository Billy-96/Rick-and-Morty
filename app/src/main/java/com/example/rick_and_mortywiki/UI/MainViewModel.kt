package com.example.rick_and_mortywiki.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_mortywiki.Model.DataClasses.CharacterNet
import com.example.rick_and_mortywiki.Model.DataClasses.Episode
import com.example.rick_and_mortywiki.Model.DataClasses.EpisodeNet
import com.example.rick_and_mortywiki.Model.DataClasses.LocationNet
import com.example.rick_and_mortywiki.Model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    val liveDatacharacters = MutableLiveData<CharacterNet>()
    val liveDataLocations = MutableLiveData<LocationNet>()
    val livedataEpisodes = MutableLiveData<EpisodeNet>()
    val liveDataOneEpisode = MutableLiveData<Episode>()
    private val repository = Repo()

    init {
        getCharacters()
        getLocations()
        getEpisodes()
    }

    private fun getEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getEpisodesFromNet("","")
                if (response.isSuccessful){
                    val episodes = response.body()
                    Log.i("MyTag",episodes.toString())
                    withContext(Dispatchers.Main){
                        livedataEpisodes.value=episodes!!
                    }
                }
            }catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    private fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getLocationsFromNet("","","")
                if (response.isSuccessful){
                    val locations = response.body()
                    Log.i("MyTag",locations.toString())
                    withContext(Dispatchers.Main){
                        liveDataLocations.value=locations!!
                    }
                }
            }catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getCharactersFromNet("","","","","")
                if (response.isSuccessful){
                    val characters = response.body()
                    Log.i("MyTag",characters.toString())
                    withContext(Dispatchers.Main){
                        liveDatacharacters.value=characters!!
                    }
                }
            }catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

     fun getOneEpisode(id:String){
       val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getOneEpisode(id)
                if (response.isSuccessful){
                    val episodeNet = response.body()
                    withContext(Dispatchers.Main){
                        liveDataOneEpisode.value = episodeNet
                    }
                }
            }catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
        viewModelScope.launch{job.join()}
    }
}