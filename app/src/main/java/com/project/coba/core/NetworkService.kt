package com.project.coba.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    inline fun <reified I> build() : I {
        return builder("https://api.coindesk.com/")
    }

    inline fun <reified I> builder(api: String) : I {
        val retrofit = Retrofit.Builder()
            .baseUrl(api)
            .client(getOKHttp())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(I::class.java)
    }

    fun getOKHttp() : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getHTTPLoggingInterceptor())
        .connectTimeout(59, TimeUnit.SECONDS)
        .writeTimeout(59, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private fun getHTTPLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}