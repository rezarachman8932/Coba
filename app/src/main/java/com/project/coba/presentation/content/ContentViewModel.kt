package com.project.coba.presentation.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.coba.core.ResultState
import com.project.coba.core.Status
import com.project.coba.data.remote.model.content.ContentDataModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ContentViewModel(private val contentUseCase: ContentUseCase): ViewModel(), KoinComponent {

    private val _getContentDetail = MutableLiveData<ResultState<ContentDataModel>>()
    val getContentDetail: LiveData<ResultState<ContentDataModel>> = _getContentDetail

    fun fetchContent() {
        viewModelScope.launch {
            val response = contentUseCase.getContent()
            response.observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        _getContentDetail.postValue(ResultState.Success(it.data))
                    }
                    Status.ERROR -> {
                        _getContentDetail.postValue(
                            ResultState.Error(
                                errorCode = it.error?.code(),
                                exception = it.exception
                            )
                        )
                    }
                    else -> {
                        // todo handling progress
                    }
                }
            }
        }
    }

    fun save(dataModel: ContentDataModel) {
        viewModelScope.launch {
            contentUseCase.saveResponse(dataModel)
        }
    }

}