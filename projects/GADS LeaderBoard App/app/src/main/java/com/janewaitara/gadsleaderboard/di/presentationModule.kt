package com.janewaitara.gadsleaderboard.di

import com.janewaitara.gadsleaderboard.ui.formSubmission.SubmitFormViewModel
import com.janewaitara.gadsleaderboard.ui.learnerLeaders.LearningLeadersViewModel
import com.janewaitara.gadsleaderboard.ui.skillIQLeaders.SkillIQLeadersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LearningLeadersViewModel(get()) }
    viewModel { SkillIQLeadersViewModel(get()) }
    viewModel { SubmitFormViewModel(get()) }
}