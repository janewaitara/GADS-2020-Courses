package com.janewaitara.gadsleaderboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.janewaitara.gadsleaderboard.ui.learnerLeaders.LearnersLeaderFragment
import com.janewaitara.gadsleaderboard.ui.skillIQLeaders.SkillIQLeadersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        submit_button.background = ContextCompat.getDrawable(applicationContext, R.drawable.button_bg)

        initViewPager2()
    }

    private fun initViewPager2() {
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        view_pager2.adapter = adapter

        var names = arrayListOf("Learning Leaders", "SkillIQ Leaders")

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