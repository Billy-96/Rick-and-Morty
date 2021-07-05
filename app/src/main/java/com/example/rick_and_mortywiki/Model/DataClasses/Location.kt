package com.example.rick_and_mortywiki.Model.DataClasses

import com.example.rick_and_mortywiki.Model.DataClasses.Character
import java.io.Serializable

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
) : Serializable