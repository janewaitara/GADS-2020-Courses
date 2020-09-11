package com.janewaitara.gadsleaderboard.ui.formSubmission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janewaitara.gadsleaderboard.model.results.Failure
import com.janewaitara.gadsleaderboard.model.results.Result
import com.janewaitara.gadsleaderboard.model.results.Success
import com.janewaitara.gadsleaderboard.repository.FormRepository
import kotlinx.coroutines.launch

class SubmitFormViewModel(private val formRepository: FormRepository): ViewModel(){

    private val formSubmissionResult = MutableLiveData<Result<String>>()

    fun getFormSubmissionResult(): LiveData<Result<String>> = formSubmissionResult

    fun submitForm(firstName: String, lastName: String, emailAddress: String, gitHubLink: String) {
        viewModelScope.launch {

            formRepository.submitFormData(firstName, lastName, emailAddress, gitHubLink).let {

               if (it.isSuccessful) {
                   formSubmissionResult.postValue(Success("submitted"))
               }else{
                   formSubmissionResult.postValue(Failure("Error Submitting Form"))
               }
            }
        }
   }
}
