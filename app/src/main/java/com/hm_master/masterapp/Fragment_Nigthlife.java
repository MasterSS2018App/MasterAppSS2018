package com.hm_master.masterapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment_Nigthlife extends Fragment {
    @Nullable

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_googlemaps,container,false);
        MainActivity.Instance.getSupportActionBar().setTitle(R.string.nav_Fun);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(MainActivity.Instance);

        // Sweet Club adden
        String[] hm = MainActivity.Instance.getResources().getStringArray(R.array.location_HM);
        MainActivity.Instance.changeMapMarker(hm);

        // Sweet Club adden
        String[] sweetClub = MainActivity.Instance.getResources().getStringArray(R.array.location_SweetClub);
        MainActivity.Instance.addMarker(sweetClub);

        return myView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
