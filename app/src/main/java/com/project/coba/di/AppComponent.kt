package com.project.coba.di

import com.project.coba.core.NetworkService
import com.project.coba.core.NetworkVisibility
import com.project.coba.data.domain.APICallService
import com.project.coba.data.local.AppRoomDatabase
import com.project.coba.data.repository.ContentRepository
import com.project.coba.presentation.content.ContentUseCase
import com.project.coba.presentation.content.ContentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val singleton = module { single { NetworkVisibility(get()) } }

val localModule = module {
    single { AppRoomDatabase.getDatabase(get()).contentDao() }
}

val networkModule = module {
    single { NetworkService.build<APICallService>() }
}

val dataSourceModule = module {
    single { ContentRepository(get(), get(), get()) }
}

val useCaseModule = module {
    single { ContentUseCase(get(), get()) }
}

val viewModelModule = module {
    viewModel { ContentViewModel(get()) }
}

val appComponent: List<Module> = listOf(
    singleton,
    localModule,
    networkModule,
    dataSourceModule,
    useCaseModule,
    viewModelModule
)