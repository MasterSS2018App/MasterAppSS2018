package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Classroom extends Fragment {
    static View myView;

    public static Fragment fragListClass;
    public static Fragment fragHMMap;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPagerAdapter;
    TabLayout tabLayoutClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_classroom, container, false);

        MainActivity mainActivity = MainActivity.Instance;

        mainActivity.getSupportActionBar().setTitle(R.string.nav_Classroom);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), R.string.nav_Classroom);

        // Set up the ViewPager with the sections adapter.
        tabLayoutClass = myView.findViewById(R.id.tabClass);
        mViewPager = (ViewPager) myView.findViewById(R.id.viewerClass);
        tabLayoutClass.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayoutClass.setupWithViewPager(mViewPager);

        fragListClass = new Fragment_ClassRoom_List();
        fragHMMap = new Fragment_HM_Map();
        return myView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

