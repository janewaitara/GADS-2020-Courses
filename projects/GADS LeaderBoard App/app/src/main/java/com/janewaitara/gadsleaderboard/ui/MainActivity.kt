package com.janewaitara.gadsleaderboard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.janewaitara.gadsleaderboard.R
import com.janewaitara.gadsleaderboard.ui.formSubmission.SubmitFormActivity
import com.janewaitara.gadsleaderboard.ui.learnerLeaders.LearnersLeaderFragment
import com.janewaitara.gadsleaderboard.ui.skillIQLeaders.SkillIQLeadersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(main_toolbar)

        initViewPager2()

        submit_button.setOnClickListener {
            startActivity(Intent(this, SubmitFormActivity::class.java))
        }
    }

    private fun initViewPager2() {
        val adapter =
            ViewPagerAdapter(
                supportFragmentManager,
                lifecycle
            )
        view_pager2.adapter = adapter

        val names = arrayListOf("Learning Leaders", "SkillIQ Leaders")

        /**
         * Connecting to to tabs*/
        TabLayoutMediator(tab_layout,view_pager2){ tab, position ->
            tab.text = names[position]
        }.attach()
    }

    internal class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
        FragmentStateAdapter(fragmentManager, lifecycle) {

        private var fragments: ArrayList<Fragment> = arrayListOf(
            LearnersLeaderFragment(),
            SkillIQLeadersFragment()
        )

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment  = fragments[position]
    }
}