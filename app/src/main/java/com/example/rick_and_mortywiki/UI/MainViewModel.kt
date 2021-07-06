package com.example.rick_and_mortywiki.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_mortywiki.Model.DataClasses.*
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

    val liveDataFindCharacter = MutableLiveData<CharacterNet>()
    val liveDataFindLocation = MutableLiveData<LocationNet>()
    val liveDataFindEpisode = MutableLiveData<EpisodeNet>()

    init {
        getCharacters()
        getLocations()
        getEpisodes()
    }

    fun getEpisodes(page:Int = 1,name:String = "",episode:String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getEpisodesFromNet(page,name, episode)
                if (response.isSuccessful) {
                    val episodes = response.body()
                    Log.i("MyTag", episodes.toString())
                    withContext(Dispatchers.Main) {
                        livedataEpisodes.value = episodes!!
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    fun getLocations(page:Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getLocationsFromNet(page,"", "", "")
                if (response.isSuccessful) {
                    val locations = response.body()
                    Log.i("MyTag", locations.toString())
                    withContext(Dispatchers.Main) {
                        liveDataLocations.value = locations!!
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    fun getCharacters(page:Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getCharactersFromNet(page,"", "", "", "", "")
                if (response.isSuccessful) {
                    val characters = response.body()
                    Log.i("MyTag", characters.toString())
                    withContext(Dispatchers.Main) {
                        liveDatacharacters.value = characters!!
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    fun getOneEpisode(id: String) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getOneEpisode(id)
                if (response.isSuccessful) {
                    val episodeNet = response.body()
                    withContext(Dispatchers.Main) {
                        liveDataOneEpisode.value = episodeNet
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
        viewModelScope.launch { job.join() }
    }

    fun findCharacter( name:String, gender:String, status: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.findCharacter(name,gender, status)
            if (response.isSuccessful) {
                val character = response.body()
                Log.i("MySwag", character.toString())
                withContext(Dispatchers.Main) {

                    liveDataFindCharacter.value = character!!
                }

            }
        }
    }

    fun findLocations( name:String, type:String, dimension: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.findLocation(name,type, dimension)
            if (response.isSuccessful) {
                val character = response.body()
                Log.i("MySwag", character.toString())
                withContext(Dispatchers.Main) {

                    liveDataFindLocation.value = character!!
                }

            }
        }
    }
    fun findEpisode( name:String) {

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.findEpisode(name)
            if (response.isSuccessful) {
                val character = response.body()
                Log.i("MySwag", character.toString())
                withContext(Dispatchers.Main) {
                    liveDataFindEpisode.value = character!!
                }

            }
        }
    }
}