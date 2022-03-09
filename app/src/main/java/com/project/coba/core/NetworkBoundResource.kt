package com.project.coba.core

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            if (!offlineMode()) {
                try {
                    val apiResponse = createCallAsync()
                    if (apiResponse.isSuccessful) {
                        apiResponse.body()?.let {
                            setValue(Resource.success(processResponse(it)))
                        } ?: kotlin.run {
                            setValue(Resource.error(apiResponse, null, null))
                        }
                    } else {
                        setValue(Resource.error(apiResponse, null, null))
                    }
                } catch (exception: Exception) {
                    setValue(Resource.error(null, null, exception))
                }
            } else {
                val dbResult = loadFromDb()
                if (shouldFetch(dbResult)) {
                    try {
                        setValue(Resource.loading(dbResult))
                        val apiResponse = createCallAsync()
                        if (apiResponse.isSuccessful) {
                            apiResponse.body()?.let {
                                saveCallResults(processResponse(it))
                            }
                            setValue(Resource.success(loadFromDb()))
                        } else {
                            setValue(Resource.error(apiResponse, dbResult, null))
                        }
                    } catch (exception: Exception) {
                        setValue(Resource.error(null, loadFromDb(), exception))
                    }
                } else {
                    setValue(Resource.success(dbResult))
                }
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) result.postValue(newValue)
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun offlineMode(): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType?

    @MainThread
    protected abstract suspend fun createCallAsync(): Response<RequestType>
}