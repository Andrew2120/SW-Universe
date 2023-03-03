package com.andrew.starwars.domain.model

import android.os.Parcelable
import com.andrew.starwars.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanetModel(
    val climate: String,
    val image :  Int = icons.random(),
    val diameter: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbitalPeriod: String,
    val population: String,
    val residents: List<String>,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
) : Parcelable

val icons = arrayOf(
    R.drawable.pluto_svgrepo_com,
    R.drawable.asteroid_2_svgrepo_com,
    R.drawable.neptune_svgrepo_com,
    R.drawable.venus_svgrepo_com,
    R.drawable.earth_svgrepo_com,
    R.drawable.saturn_svgrepo_com,
)
