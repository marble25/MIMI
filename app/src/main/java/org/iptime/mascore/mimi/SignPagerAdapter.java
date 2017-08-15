package org.iptime.mascore.mimi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnote on 2017-08-15.
 */

public class SignPagerAdapter extends FragmentPagerAdapter {
    public SignPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Signup1Fragment();
            case 1:
                return new Signup2Fragment();
            case 2:
                return new Signup3Fragment();
            case 3:
                return new Signup4Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}