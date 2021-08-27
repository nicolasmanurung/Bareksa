package com.excercise.bareksatest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.excercise.bareksatest.databinding.ActivityMainBinding
import com.excercise.bareksatest.ui.main.DanaKelolaanFragment
import com.excercise.bareksatest.ui.main.ImbalHasilFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Imbal Hasil"
                }
                1 -> {
                    tab.text = "Dana Kelolaan"
                }
            }
        }.attach()

        binding.viewPager.isUserInputEnabled = false
    }



    class ViewPagerAdapter internal constructor(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ImbalHasilFragment()
                1 -> return DanaKelolaanFragment()
            }
            return ImbalHasilFragment()
        }

        override fun getItemCount(): Int {
            return 2
        }
    }
}