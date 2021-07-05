package com.example.rick_and_mortywiki.Interfaces

import android.app.Person
import android.location.Location
import androidx.fragment.app.Fragment
import com.example.rick_and_mortywiki.Model.DataClasses.Character
import com.example.rick_and_mortywiki.Model.DataClasses.Episode

interface ClickImp {
    fun onCardClickPerson(position:Int,list:List<Character>)
    fun onCardClickLocation(position: Int,list: List<com.example.rick_and_mortywiki.Model.DataClasses.Location>)
    fun onCardClickEpisode(position: Int,list: List<Episode>)
}