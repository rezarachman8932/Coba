package com.project.coba.data.remote.map

import com.squareup.moshi.Json

data class ContentDataMap(
    @Json(name = "updated") val updated: String,
    @Json(name = "usd_rate") val usd_rate: String,
    @Json(name = "gbp_rate") val gbp_rate: String,
    @Json(name = "eur_rate") val eur_rate: String
)
