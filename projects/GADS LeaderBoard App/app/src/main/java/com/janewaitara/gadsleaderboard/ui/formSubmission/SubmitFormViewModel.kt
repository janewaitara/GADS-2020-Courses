package com.janewaitara.gadsleaderboard.ui.formSubmission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janewaitara.gadsleaderboard.model.results.Result
import com.janewaitara.gadsleaderboard.repository.FormRepository
import kotlinx.coroutines.launch

class SubmitFormViewModel(private val formRepository: FormRepository): ViewModel(){

    private val formSubmissionResult = MutableLiveData<Result<Void>>()

    fun getFormSubmissionResult(): LiveData<Result<Void>> = formSubmissionResult

    fun submitForm(firstName: String, lastName: String, emailAddress: String, gitHubLink: String) {
        viewModelScope.launch {

            val result = formRepository.submitFormData(firstName, lastName, emailAddress, gitHubLink)

            formSubmissionResult.postValue(result)
        }
   }


}
