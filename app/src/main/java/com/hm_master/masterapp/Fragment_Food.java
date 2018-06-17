package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Food extends Fragment {
    @Nullable

    View myView;

    Button btnStu;
    Button btnK6;
    Button btnBK;
    Button btnMCD;
    Button btnSubway;
    Button btnLostria;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_food,container,false);
        MainActivity.Instance.getSupportActionBar().setTitle(R.string.nav_Food);

        btnStu = myView.findViewById(R.id.btn_food_stucafe);
        btnStu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnStu.setTag(R.array.location_StuCafe);

        btnK6 = myView.findViewById(R.id.btn_food_k6);
        btnK6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnK6.setTag(R.array.location_K6);

        btnBK = myView.findViewById(R.id.btn_food_bking);
        btnBK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnBK.setTag(R.array.location_BK);

        btnMCD = myView.findViewById(R.id.btn_food_mcdonalds);
        btnMCD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnMCD.setTag(R.array.location_Mc_Donalds);

        btnSubway = myView.findViewById(R.id.btn_food_subway);
        btnSubway.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnSubway.setTag(R.array.location_Subway);

        btnLostria = myView.findViewById(R.id.btn_food_osteria);
        btnLostria.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showMapWithFoodLocation(v);
            }
        });
        btnLostria.setTag(R.array.location_LOsteria);
        
        return myView;
    }

    public void showMapWithFoodLocation(View v) {

        String[] nameLatLon = (String[]) MainActivity.Instance.getResources().getStringArray((int)v.getTag());
        MainActivity.Instance.changeMapMarker(nameLatLon);
        MainActivity.Instance.ChangeFragment(R.string.nav_GoogleMaps);

    }
}
