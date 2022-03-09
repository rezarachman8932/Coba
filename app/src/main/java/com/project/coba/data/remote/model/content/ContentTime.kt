package com.project.coba.data.remote.model.content

import com.squareup.moshi.Json

data class ContentTime(
    @Json(name = "updated") val updated: String,
    @Json(name = "updatedISO") val updatedISO: String,
    @Json(name = "updateduk") val updateduk: String
)