package com.faisal.dc.apprise.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.faisal.dc.apprise.HomeFragment
import com.faisal.dc.apprise.ListFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return ListFragment()
            }

            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "List Page"
            }

        }
        return super.getPageTitle(position)
    }

}