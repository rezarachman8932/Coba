package com.project.coba.data.repository

import androidx.lifecycle.LiveData
import com.project.coba.core.NetworkBoundResource
import com.project.coba.core.NetworkVisibility
import com.project.coba.core.Resource
import com.project.coba.data.domain.APICallService
import com.project.coba.data.local.dao.ContentDao
import com.project.coba.data.mapper.toEntity
import com.project.coba.data.remote.model.content.ContentDataModel
import retrofit2.Response

class ContentRepository(
    private val apiService: APICallService,
    private val contentDao: ContentDao,
    private val network: NetworkVisibility
) {

    suspend fun getContent(): LiveData<Resource<ContentDataModel>> {
        return object : NetworkBoundResource<ContentDataModel, ContentDataModel>() {
            override fun processResponse(response: ContentDataModel): ContentDataModel {
                return response
            }

            override suspend fun saveCallResults(items: ContentDataModel) {
                contentDao.deleteContent()
                contentDao.insert(items.toEntity())
            }

            override fun shouldFetch(data: ContentDataModel?): Boolean {
                val isAvailable = network.hasNetworkAvailable()
                return isAvailable || data == null
            }

            override suspend fun loadFromDb(): ContentDataModel? {
                return null
            }

            override suspend fun createCallAsync(): Response<ContentDataModel> {
                return apiService.getContent()
            }

            override fun offlineMode(): Boolean {
                return false
            }
        }.build().asLiveData()
    }

}