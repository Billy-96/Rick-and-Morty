package com.example.rick_and_mortywiki.Model.DataClasses

import android.os.Parcelable
import java.io.Serializable

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species:String,
    val type: String,
    val gender:String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>
):Serializable