package com.andrew.starwars.data.dto


import com.google.gson.annotations.SerializedName

data class PaginationBaseResponse<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<T>
)

