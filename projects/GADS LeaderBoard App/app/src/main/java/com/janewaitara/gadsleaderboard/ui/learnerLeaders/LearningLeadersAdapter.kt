package com.janewaitara.gadsleaderboard.ui.learnerLeaders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.janewaitara.gadsleaderboard.App
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.model.data.LearningLeaders
import kotlinx.android.synthetic.main.leaders_list.view.*

class LearningLeadersAdapter : RecyclerView.Adapter<LearningLeadersAdapter.LearningLeadersViewHolder>(){

    private var learningLeaders = ArrayList<LearningLeaders>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningLeadersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leaders_list, parent, false)
        return LearningLeadersViewHolder(view)
    }

    override fun getItemCount(): Int = learningLeaders.size

    override fun onBindViewHolder(holder: LearningLeadersViewHolder, position: Int) {
        holder.bind(learningLeaders[position])
    }

    internal fun setLearningLeaders(learningLeaders: List<LearningLeaders>){
        this.learningLeaders.apply {
            clear()
            addAll(learningLeaders)
            notifyDataSetChanged()
        }
    }

    class LearningLeadersViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        fun bind(learningLeaders: LearningLeaders){
            itemView.apply {
                learning_leader_name.text = learningLeaders.name
                learning_leader_hours_country.text = App.getResources().getString(R.string.leaders_hrs_and_country_entry, learningLeaders.hours.toString(), learningLeaders.country )

                Glide.with(learning_leader_image.context)
                    .load(learningLeaders.badgeUrl)
                    .into(learning_leader_image)
            }
        }
    }

}