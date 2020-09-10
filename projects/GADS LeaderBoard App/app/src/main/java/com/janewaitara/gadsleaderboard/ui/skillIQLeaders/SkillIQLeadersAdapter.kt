package com.janewaitara.gadsleaderboard.ui.skillIQLeaders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.janewaitara.gadsleaderboard.App
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.model.data.SkillIQLeaders
import kotlinx.android.synthetic.main.skilliq_list.view.*

class SkillIQLeadersAdapter: RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQLeadersViewHolder>(){

    private var skillIQLeaders = ArrayList<SkillIQLeaders>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQLeadersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skilliq_list, parent, false)
        return SkillIQLeadersViewHolder(view)
    }

    override fun getItemCount(): Int  = skillIQLeaders.size

    override fun onBindViewHolder(holder: SkillIQLeadersViewHolder, position: Int) {
        holder.bind(skillIQLeaders[position])
    }

    internal fun setSkillIQLeaders(skillIQLeaders: List<SkillIQLeaders>){
        this.skillIQLeaders.apply {
            clear()
            addAll(skillIQLeaders)
            notifyDataSetChanged()
        }
    }

    class SkillIQLeadersViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        fun bind(skillIQLeaders: SkillIQLeaders){
            itemView.apply {
                skillIQ_leader_name.text = skillIQLeaders.name
                skillIQ_leader_skills_country.text = App.getResources().getString(R.string.leaders_skillIQ_and_country_entry, skillIQLeaders.score.toString(), skillIQLeaders.country)

                Glide.with(skillIQ_leader_image.context)
                    .load(skillIQLeaders.badgeUrl)
                    .into(skillIQ_leader_image)
            }
        }
    }
}