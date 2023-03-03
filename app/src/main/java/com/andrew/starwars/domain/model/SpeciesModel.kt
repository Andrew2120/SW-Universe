package com.andrew.starwars.domain.model

import android.os.Parcelable
import com.andrew.starwars.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpeciesModel(
    val image :  Int = SpeciesIcon.random(),
    val averageHeight: String,
    val averageLifespan: String,
    val classification: String,
    val designation: String,
    val eyeColors: String,
    val films: List<String>,
    val hairColors: String,
    val homeworld: String?,
    val language: String,
    val name: String,
    val people: List<String>,
    val skinColors: String,

) : Parcelable

val SpeciesIcon = arrayOf(
    R.drawable.morty_svgrepo_com,
    R.drawable.rick_svgrepo_com,
    R.drawable.princess_leia_svgrepo_com,
    R.drawable.darth_vader_svgrepo_com,
    R.drawable.chewbacca_svgrepo_com,
    R.drawable.atronaut_svgrepo_com,
)
