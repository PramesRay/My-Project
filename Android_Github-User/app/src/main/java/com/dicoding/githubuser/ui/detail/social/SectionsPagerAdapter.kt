package com.dicoding.githubuser.ui.detail.social

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowsInfoFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowsInfoFragment.ARG_POSITION, position + 1)
            putString(FollowsInfoFragment.ARG_USERNAME, username)
        }
        return fragment
    }
}