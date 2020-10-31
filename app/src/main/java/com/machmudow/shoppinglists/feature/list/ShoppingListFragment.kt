package com.machmudow.shoppinglists.feature.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentListBinding

class ShoppingListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        initPager()
        setTabIcons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initPager() {
        val adapter = ListPagerAdapter(context, childFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun setTabIcons() {
        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_list)
        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_archived)
    }
}