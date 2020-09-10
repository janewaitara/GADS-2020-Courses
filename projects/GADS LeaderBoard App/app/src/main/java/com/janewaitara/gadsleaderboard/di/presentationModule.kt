package com.janewaitara.gadsleaderboard.di

import com.janewaitara.gadsleaderboard.ui.learnerLeaders.LearningLeadersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LearningLeadersViewModel(get()) }
}