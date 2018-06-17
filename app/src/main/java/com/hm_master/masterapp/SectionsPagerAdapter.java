package com.hm_master.masterapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    int Caller;

    // tab titles
    private String[] tabTitles = new String[]{"Freie Räume", "Übersichtskarte"};
    public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm,int caller) {
        super(fm);
        Caller = caller;
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (Caller)
        {
            case(R.string.nav_PcRooms):

                switch (position)
                {
                    case(0):
                        //return new Fragment_PCRoom_List();
                        return Fragment_PCRoom.fragListPc;


                    case(1):
                        //return new Fragment_HM_Map();
                        return Fragment_PCRoom.fragHMMap;

                }

            case(R.string.nav_Classroom):

                switch (position)
                {
                    case(0):
                        return new Fragment_PCRoom_List();

                    case(1):
                        return new Fragment_HM_Map();
                    default:
                        return null;
                }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}