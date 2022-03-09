package com.project.coba.core

import retrofit2.Response
import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T?, val error: Response<*>?, val exception: Exception?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(error: Response<*>?, data: T?, exception: Exception?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error,
                exception
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null,
                null
            )
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}