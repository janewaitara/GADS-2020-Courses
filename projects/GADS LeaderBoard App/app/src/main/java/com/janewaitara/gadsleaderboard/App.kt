package com.janewaitara.gadsleaderboard

import android.app.Application
import android.content.res.Resources
import com.janewaitara.gadsleaderboard.di.networkModule
import com.janewaitara.gadsleaderboard.di.presentationModule
import com.janewaitara.gadsleaderboard.di.remoteApiModule
import com.janewaitara.gadsleaderboard.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    companion object{
        private lateinit var instance: App
        private lateinit var res : Resources

        fun getResources() = res
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        res = resources

        startingKoin()
    }

    /**
     * Dependency injection with Koin*/
    private fun startingKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    remoteApiModule,
                    networkModule,
                    repositoryModule,
                    presentationModule
                )
            )
        }
    }
}