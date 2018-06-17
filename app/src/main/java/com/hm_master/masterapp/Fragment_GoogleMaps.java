package com.hm_master.masterapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class Fragment_GoogleMaps extends Fragment {
    @Nullable

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_googlemaps,container,false);
        MainActivity.Instance.getSupportActionBar().setTitle(R.string.nav_GoogleMaps);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(MainActivity.Instance);

        return myView;
    }
}