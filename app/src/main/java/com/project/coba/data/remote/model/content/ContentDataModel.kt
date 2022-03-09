package com.project.coba.data.remote.model.content

import com.squareup.moshi.Json

data class ContentDataModel(
    @Json(name = "time") val time: ContentTime,
    @Json(name = "bpi") val bpi: ContentBPI,
)