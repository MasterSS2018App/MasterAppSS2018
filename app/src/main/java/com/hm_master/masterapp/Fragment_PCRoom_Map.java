package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_PCRoom_Map extends Fragment {
    View myView;

    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (myView == null) {
            myView = inflater.inflate(R.layout.layout_pcroom_list, container, false);

            mTabHost = (FragmentTabHost) myView.findViewById(android.R.id.tabhost);
            mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

            mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"), Fragment_PCRoom_List.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"), Fragment_PCRoom_Map.class, null);
        }
        return myView;
    }

    public static Fragment_PCRoom newInstance() {
        Fragment_PCRoom fragment = new Fragment_PCRoom();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

