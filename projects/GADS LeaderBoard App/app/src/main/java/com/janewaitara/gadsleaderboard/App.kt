package com.janewaitara.gadsleaderboard

import android.app.Application
import com.janewaitara.gadsleaderboard.di.networkModule
import com.janewaitara.gadsleaderboard.di.remoteApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    companion object{
        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startingKoin()
    }

    private fun startingKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    remoteApiModule,
                    networkModule
                )
            )
        }
    }
}