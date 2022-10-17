package com.faisal.dc.apprise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.faisal.dc.apprise.adapter.PageAdapter
import com.faisal.dc.apprise.databinding.ActivityDeashBoardBinding
import com.faisal.dc.apprise.databinding.ActivityMainBinding

class DeashBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDeashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_deash_board)



        binding.viewPager.adapter = PageAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


}

