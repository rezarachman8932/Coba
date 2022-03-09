package com.project.coba.data.mapper

import com.project.coba.data.local.entity.ContentEntity
import com.project.coba.data.remote.map.ContentDataMap
import com.project.coba.data.remote.model.content.ContentDataModel

fun ContentDataModel.toEntity(): ContentEntity {
    return ContentEntity(
        updated = time.updated,
        usd_rate = bpi.USD.rate,
        gbp_rate = bpi.GBP.rate,
        eur_rate = bpi.EUR.rate
    )
}

fun ContentEntity.toDomain(): ContentDataMap {
        return ContentDataMap(
            updated = updated,
            usd_rate = usd_rate,
            gbp_rate = gbp_rate,
            eur_rate = eur_rate
        )
}