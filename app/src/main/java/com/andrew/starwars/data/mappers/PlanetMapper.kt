package com.andrew.starwars.data.mappers

import com.andrew.starwars.data.dto.PlanetDto
import com.andrew.starwars.domain.model.PlanetModel

fun PlanetDto.toPlanet ():PlanetModel {
    return PlanetModel(
        name = name,
        rotationPeriod = rotationPeriod,
        orbitalPeriod = orbitalPeriod,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surfaceWater = surfaceWater,
        population = population,
        residents = residents,
        films = films,
    )
}