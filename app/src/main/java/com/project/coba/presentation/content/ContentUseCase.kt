package com.project.coba.presentation.content

import com.project.coba.data.local.dao.ContentDao
import com.project.coba.data.mapper.toEntity
import com.project.coba.data.remote.model.content.ContentDataModel
import com.project.coba.data.repository.ContentRepository

class ContentUseCase(private val contentRepository: ContentRepository, private val contentDao: ContentDao) {
    suspend fun getContent() = contentRepository.getContent()
    suspend fun saveResponse(dataModel: ContentDataModel) {
        contentDao.deleteContent()
        contentDao.insert(dataModel.toEntity())
    }
}