package com.andrew.starwars.domain.model

import android.os.Parcelable
import com.andrew.starwars.utils.convertDate
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Parcelize
data class MovieModel(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episodeId: Int,
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val vehicles: List<String>
) : Parcelable

