package com.andrew.starwars.domain.model

import android.os.Parcelable
import com.andrew.starwars.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonModel(
    val birthYear: String,
    val eyeColor: String,
    val image: Int = images.random(),
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val backgroundColor: Int = colors.random(),
) : Parcelable

val colors = arrayOf(
    R.color.baby_blue,
    R.color.dark__green,
    R.color.green,
    R.color.orange,
    R.color.purple,
    R.color.red,
)

val images = arrayOf(
    R.drawable.alien_5_svgrepo_com,
    R.drawable.alien_2_svgrepo_com,
    R.drawable.alien_4_svgrepo_com,
    R.drawable.alien_3_svgrepo_com,
    R.drawable.alien_1_svgrepo_com,
)
