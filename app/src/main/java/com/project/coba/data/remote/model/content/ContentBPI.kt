package com.project.coba.data.remote.model.content

import com.squareup.moshi.Json

data class ContentBPI(
    @Json(name = "USD") val USD: ContentRegionCountry,
    @Json(name = "GBP") val GBP: ContentRegionCountry,
    @Json(name = "EUR") val EUR: ContentRegionCountry
)
