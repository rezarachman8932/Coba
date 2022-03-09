package com.project.coba

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.project.coba.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

@KoinApiExtension
class Application : MultiDexApplication(), KoinComponent {

    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@Application)
            workManagerFactory()
            modules(appComponent)
        }
    }

}