package com.machmudow.shoppinglists.feature.list

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.feature.list.archived.ArchivedListFragment
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment

class ListPagerAdapter(private val context: Context?, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CurrentListFragment()
            else -> ArchivedListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.resources?.getText(R.string.shopping_lists)
            else -> context?.resources?.getText(R.string.archived_shopping_lists)
        }
    }
}