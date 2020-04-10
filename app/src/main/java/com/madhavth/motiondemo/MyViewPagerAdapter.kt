package com.madhavth.motiondemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyAdapter(fa: FragmentActivity): FragmentStateAdapter(fa)
{
    private val listItem = arrayListOf<String>("Stay Safe","Stay Home","Just Stay Home")

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(listItem[position])
    }

}