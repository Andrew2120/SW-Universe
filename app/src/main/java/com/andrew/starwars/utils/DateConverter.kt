package com.andrew.starwars.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertDate(originalDate: String): String {
    val date = LocalDate.parse(originalDate)
    return date.format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))
}