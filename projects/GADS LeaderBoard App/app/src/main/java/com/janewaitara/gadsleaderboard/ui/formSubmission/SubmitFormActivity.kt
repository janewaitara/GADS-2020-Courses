package com.janewaitara.gadsleaderboard.ui.formSubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.model.results.Failure
import com.janewaitara.gadsleaderboard.model.results.Result
import com.janewaitara.gadsleaderboard.model.results.Success
import com.janewaitara.gadsleaderboard.utils.isVisible
import kotlinx.android.synthetic.main.activity_submit_form.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubmitFormActivity : AppCompatActivity() {

   private val submitFormViewModel: SubmitFormViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_form)

        submit_btn.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {

        val firstName = first_name.text.toString().trim()
        val lastName = last_name.text.toString().trim()
        val emailAddress = email.text.toString().trim()
        val githubLink = github_link.text.toString().trim()

        if (firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            emailAddress.isNotEmpty() &&
            githubLink.isNotEmpty() ){

            showEditTexts(false)

            showConfirmSubmissionDialog(firstName,lastName,emailAddress,githubLink)
        }
    }
    private fun showEditTexts(boolean: Boolean) {
        first_name.isVisible(boolean)
        last_name.isVisible(boolean)
        email.isVisible(boolean)
        github_link.isVisible(boolean)
        submit_btn.isVisible(boolean)
    }

    private fun showConfirmSubmissionDialog(
        firstName: String,
        lastName: String,
        emailAddress: String,
        githubLink: String
    ) {
        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.confirm_form_data_submission)

        dialog.findViewById<Button>(R.id.confirm_btn).setOnClickListener {
            dialog.dismiss()
            showEditTexts(false)
            showAnimation(true)
            submitFormViewModel.submitForm(firstName,lastName,emailAddress,githubLink)

            submitFormViewModel.getFormSubmissionResult().observe(this, Observer {
                it?.let {
                    performAction(it)
                }
            })
        }
        dialog.findViewById<ImageView>(R.id.close).setOnClickListener {
            dialog.dismiss()
            showEditTexts(true)
        }
        dialog.show()
    }

    private fun showAnimation(showAnim: Boolean) {
        loading_anim_image.isVisible(showAnim)
    }

    private fun performAction(result: Result<String>) {
        when(result){
            is Success -> {
                showAnimation(false)
                Toast.makeText(this, result.data, Toast.LENGTH_LONG).show()
                successDialog()
            }
            is Failure-> {
                showAnimation(false)
                Toast.makeText(this, result.error, Toast.LENGTH_LONG).show()
                errorDialog()
            }
        }
    }

    private fun successDialog() {

        val dialog = MaterialDialog(this)
            .customView(R.layout.submission_successful_dialog)
            .onDismiss {
                showEditTexts(true)
            }
        dialog.show()

    }

    private fun errorDialog() {

        val dialog = MaterialDialog(this)
            .customView(R.layout.submission_failed)
            .onDismiss {
                showEditTexts(true)
            }
        dialog.show()
    }

}