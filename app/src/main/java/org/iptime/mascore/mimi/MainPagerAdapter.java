package org.iptime.mascore.mimi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by xnote on 2017-08-17.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    int numOfTabs;

    public MainPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MeetingFragment();
            case 2:
                return new StoreFragment();
            case 3:
                return new MypageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
