package com.project.coba.data.domain

import com.project.coba.data.remote.model.content.ContentDataModel
import retrofit2.Response
import retrofit2.http.GET

interface APICallService {
    @GET("v1/bpi/currentprice.json")
    suspend fun getContent() : Response<ContentDataModel>
}