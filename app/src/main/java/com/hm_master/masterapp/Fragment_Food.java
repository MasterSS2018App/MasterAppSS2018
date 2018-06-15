package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Food extends Fragment {
    @Nullable

    View myView;
    Button btnStucafe;
    Button btnK6;
    Button btnBking;
    Button btnMcdonalds;
    Button btnSubway;
    Button btnOsteria;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_food,container,false);

        btnStucafe = myView.findViewById(R.id.btn_stucafe);
        btnStucafe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_pc_room);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnK6 = myView.findViewById(R.id.btn_k6);
        btnK6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_classroom);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnBking = myView.findViewById(R.id.btnbking);
        btnBking.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_library);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnMcdonalds = myView.findViewById(R.id.btnmcdonalds);
        btnMcdonalds.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_mvv);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnSubway = myView.findViewById(R.id.btnsubway);
        btnSubway.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_mvv);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        btnOsteria = myView.findViewById(R.id.btnosteria);
        btnOsteria.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_fun);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });


        return myView;
    }
}
