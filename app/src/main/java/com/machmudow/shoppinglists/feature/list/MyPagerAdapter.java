package com.machmudow.shoppinglists.feature.list;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.machmudow.shoppinglists.R;
import com.machmudow.shoppinglists.feature.list.archived.ArchivedListFragment;
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment;
import org.jetbrains.annotations.NotNull;

public class MyPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public MyPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CurrentListFragment.Companion.newInstance();
            case 1:
                return ArchivedListFragment.Companion.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getText(R.string.shopping_lists);
            case 1:
                return context.getResources().getText(R.string.archived_shopping_lists);
            default:
                return null;
        }
    }
}