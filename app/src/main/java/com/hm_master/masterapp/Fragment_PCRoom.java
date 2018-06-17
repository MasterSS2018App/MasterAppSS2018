package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_PCRoom extends Fragment {
    static View myView;

    public static Fragment fragListPc;
    public static Fragment fragHMMap;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPagerAdapterPC;
    TabLayout tabLayoutPc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            myView = inflater.inflate(R.layout.layout_pcroom, container, false);

            MainActivity mainActivity = MainActivity.Instance;

            mainActivity.getSupportActionBar().setTitle(R.string.nav_PcRooms);

            mSectionsPagerAdapterPC = new SectionsPagerAdapter(getChildFragmentManager(),R.string.nav_PcRooms);

            // Set up the ViewPager with the sections adapter.
            tabLayoutPc = myView.findViewById(R.id.tabPc);
            mViewPager = (ViewPager) myView.findViewById(R.id.viewerPc);
            tabLayoutPc.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            mViewPager.setAdapter(mSectionsPagerAdapterPC);
            tabLayoutPc.setupWithViewPager(mViewPager);

            fragListPc = new Fragment_PCRoom_List();
            fragHMMap = new Fragment_HM_Map();
        return myView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

