package com.ymnd.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.ymnd.android.BuildConfig
import com.ymnd.android.databinding.FragmentDebugMenuViewPagerBinding
import com.ymnd.android.debug.DebugMenuCategory

class DebugMenuViewPagerFragment : Fragment() {

    companion object {
        fun newInstance() = DebugMenuViewPagerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDebugMenuViewPagerBinding.inflate(inflater, null, false)
        val fm = requireNotNull(fragmentManager)
        val adapter = DebugMenuViewPagerAdapter(fm)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.toolbar.title = BuildConfig.BUILD_TYPE + " / " + BuildConfig.VERSION_NAME
        binding.toolbar.setNavigationOnClickListener {
            activity?.finish()
        }
        return binding.root
    }

    private class DebugMenuViewPagerAdapter(
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

        private val list = DebugMenuCategory.values().toList()

        override fun getItem(position: Int) = list[position].fragment

        override fun getCount() = list.size

        override fun getPageTitle(position: Int) = list[position].title
    }

}