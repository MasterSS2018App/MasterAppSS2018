package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import static android.view.View.*;


public class Fragment_Home extends Fragment{
    @Nullable

    View myView;
    Button btnPcRoom;
    Button btnClass;
    Button btnLib;
    Button btnFood;
    Button btnMVV;
    Button btnFun;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_home, container, false);

        btnPcRoom = myView.findViewById(R.id.btn_pcRoom);
        btnPcRoom.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_pc_room);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnClass = myView.findViewById(R.id.btn_classroom);
        btnClass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_classroom);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnLib = myView.findViewById(R.id.btnLib);
        btnLib.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_library);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnFood = myView.findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_food);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnMVV = myView.findViewById(R.id.btnMvv);
        btnMVV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_mvv);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnFun = myView.findViewById(R.id.btnFun);
        btnFun.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_fun);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });


        return myView;
    }


}
