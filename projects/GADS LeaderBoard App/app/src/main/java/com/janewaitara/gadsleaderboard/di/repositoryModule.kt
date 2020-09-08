package com.janewaitara.gadsleaderboard.di

import com.janewaitara.gadsleaderboard.repository.LeaderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LeaderRepository(get()) }
}