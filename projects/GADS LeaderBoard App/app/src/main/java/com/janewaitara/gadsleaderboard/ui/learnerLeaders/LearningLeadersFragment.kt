package com.janewaitara.gadsleaderboard.ui.learnerLeaders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.model.results.Failure
import com.janewaitara.gadsleaderboard.model.results.Success
import com.janewaitara.gadsleaderboard.utils.isVisible
import kotlinx.android.synthetic.main.fragment_learners_leader.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LearnersLeaderFragment : Fragment() {

    private val learningLeadersViewModel: LearningLeadersViewModel by viewModel()

    private val learningLeadersAdapter = LearningLeadersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learners_leader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLearningLeaders()
    }

    private fun setUpRecyclerView() {
        learnerLeaderRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        learnerLeaderRecyclerView.adapter =  learningLeadersAdapter
    }


    private fun setUpLearningLeaders() {
        learningLeadersViewModel.getLearningLeaders()

        learningLeadersViewModel.getLearningLeadersViewState().observe(viewLifecycleOwner, Observer{ learningLeaderViewState ->
            learningLeaderViewState?.let {
               onDetailsViewStateChanged(it)
            }
        })
    }

    private fun onDetailsViewStateChanged(learningLeaderViewState: LearningLeadersViewState) {
        when(learningLeaderViewState){
            LearningLeadersLoading -> showLoading(true)
            is LearningLeadersSuccess -> {
                when(learningLeaderViewState.learningLeaders){
                    is Success -> {
                        showLoading(false)
                        learningLeadersAdapter.setLearningLeaders(learningLeaderViewState.learningLeaders.data)
                        Toast.makeText(context, learningLeaderViewState.learningLeaders.data.toString(), Toast.LENGTH_LONG).show()
                    }
                    is Failure -> {
                        Toast.makeText(context, learningLeaderViewState.learningLeaders.error.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun showLoading(loadingStatus: Boolean) {
        leader_loading_anim.isVisible(loadingStatus)
    }

}