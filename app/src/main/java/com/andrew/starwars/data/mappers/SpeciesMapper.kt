package com.andrew.starwars.data.mappers

import com.andrew.starwars.data.dto.MovieDto
import com.andrew.starwars.data.dto.PersonDto
import com.andrew.starwars.data.dto.PlanetDto
import com.andrew.starwars.data.dto.SpeciesDto
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.domain.model.SpeciesModel

fun SpeciesDto.toSpecies(): SpeciesModel {
    return SpeciesModel(
        name = name,
        classification = classification,
        designation = designation,
        averageHeight = averageHeight,
        skinColors = skinColors,
        hairColors = hairColors,
        eyeColors = eyeColors,
        averageLifespan = averageLifespan,
        homeworld = homeworld,
        language = language,
        people = people,
        films = films,
    )


}