package com.andrew.starwars.data.mappers

import com.andrew.starwars.data.dto.MovieDto
import com.andrew.starwars.data.dto.PersonDto
import com.andrew.starwars.data.dto.PlanetDto
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.utils.convertDate

fun MovieDto.toMovie(): MovieModel {
    return MovieModel(
        characters = characters,
        created = created,
        director = director,
        edited = edited,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        planets = planets,
        producer = producer,
        releaseDate = convertDate(releaseDate),
        species = species,
        starships = starships,
        title = title,
        vehicles = vehicles
    )


}