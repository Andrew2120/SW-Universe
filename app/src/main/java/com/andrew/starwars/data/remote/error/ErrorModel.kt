package com.andrew.starwars.data.remote.error


import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("detail")
    val detail: String,
)

