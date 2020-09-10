package com.janewaitara.gadsleaderboard.ui.skillIQLeaders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.model.results.Failure
import com.janewaitara.gadsleaderboard.model.results.Success
import com.janewaitara.gadsleaderboard.utils.isVisible
import kotlinx.android.synthetic.main.fragment_skill_i_q_leaders.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SkillIQLeadersFragment : Fragment() {

    private val skillIQLeadersViewModel: SkillIQLeadersViewModel by viewModel()

    private val skillIQLeadersAdapter = SkillIQLeadersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpSkillIQLeaders()
    }

    private fun setUpRecyclerView() {
        skillIQLeaderRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        skillIQLeaderRecyclerView.adapter = skillIQLeadersAdapter
    }

    private fun setUpSkillIQLeaders() {
        skillIQLeadersViewModel.getSkillIQLeaders()

        skillIQLeadersViewModel.getSkillIQLeadersViewState().observe(viewLifecycleOwner, Observer { skillIQLeadersViewState->
            skillIQLeadersViewState?.let {
                onDetailsViewStateChanged(it)
            }
        })
    }

    private fun onDetailsViewStateChanged(skillIQLeadersViewState: SkillIQLeadersViewState) {
        when(skillIQLeadersViewState){
            SkillIQLeadersLoading -> showLoading(true)
            is SkillIQLeadersSuccess -> {
                when(skillIQLeadersViewState.skillIQLeaders){
                    is Success -> {
                        showLoading(false)
                        skillIQLeadersAdapter.setSkillIQLeaders(skillIQLeadersViewState.skillIQLeaders.data)
                    }
                    is Failure -> {
                        Toast.makeText(context, skillIQLeadersViewState.skillIQLeaders.error.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun showLoading(loadingStatus: Boolean) {
        leader_loading_anim.isVisible(loadingStatus)
    }
}