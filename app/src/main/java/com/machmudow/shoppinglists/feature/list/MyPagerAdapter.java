package com.machmudow.shoppinglists.feature.list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.machmudow.shoppinglists.feature.list.archived.ArchivedListFragment;
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment;
import org.jetbrains.annotations.NotNull;

public class MyPagerAdapter extends FragmentPagerAdapter {
    String listsTitle;
    String archivedTitle;

    public MyPagerAdapter(FragmentManager fm, String listsTitle, String archivedTitle) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listsTitle = listsTitle;
        this.archivedTitle = archivedTitle;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return CurrentListFragment.Companion.newInstance();
        }
        return ArchivedListFragment.Companion.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return listsTitle;
        }
        return archivedTitle;
    }
}