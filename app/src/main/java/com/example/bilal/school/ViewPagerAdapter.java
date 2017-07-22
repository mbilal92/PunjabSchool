package com.example.bilal.school;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bilal on 5/28/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    int type,agent_id;
    Bundle b;
    private String[] tabTitles = new String[]{"Near You", "Recent"};
    public ViewPagerAdapter(FragmentManager fm,int _type, int _agent_id) {
        super(fm);
        type= _type;
        agent_id= _agent_id;
        b = new Bundle();
        b.putInt("type", type);
        b.putInt("agent_id", agent_id);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                NearByFrag fragmenttab1 = new NearByFrag();
                fragmenttab1.setArguments(b);
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                RecentFrag  fragmenttab2 = new RecentFrag();
                fragmenttab2.setArguments(b);
                return fragmenttab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
