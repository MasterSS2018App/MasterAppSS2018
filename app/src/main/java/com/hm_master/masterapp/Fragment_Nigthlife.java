package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Nigthlife extends Fragment {
    @Nullable

    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_nightlife,container,false);
        MainActivity.Instance.getSupportActionBar().setTitle(R.string.nav_Fun);
        return myView;
    }
}
