package com.project.coba.data.local.entity

import androidx.room.Entity

@Entity(tableName = "content", primaryKeys = ["updated"])
data class ContentEntity(
    val updated: String,
    val usd_rate: String,
    val gbp_rate: String,
    val eur_rate: String
)
