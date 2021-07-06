package com.example.rick_and_mortywiki.Model.DataClasses

import com.example.rick_and_mortywiki.Model.DataClasses.Character
import java.io.Serializable

data class Episode(
    val id:Int,
    val name: String,
    val date:String,
    val episode: String,
    val created:String,
    val characters:List<String>
):Serializable