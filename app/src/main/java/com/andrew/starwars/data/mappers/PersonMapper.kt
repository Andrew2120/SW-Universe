package com.andrew.starwars.data.mappers

import com.andrew.starwars.data.dto.PersonDto
import com.andrew.starwars.data.dto.PlanetDto
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel

fun PersonDto.toPerson ():PersonModel {
    return PersonModel(
        name = name,
        birthYear = birthYear,
        gender = gender,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships,
    )


}