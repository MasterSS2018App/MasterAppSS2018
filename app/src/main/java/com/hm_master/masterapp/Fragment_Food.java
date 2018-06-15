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

public class Fragment_Food extends Fragment {
    @Nullable


    View myView;
    Button btnK6;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_food,container,false);

        btnK6 = myView.findViewById(R.id.btn_k6);
        btnK6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MenuItem item = MainActivity.navigationView.getMenu().findItem(R.id.nav_k6);
                MainActivity.Instance.onNavigationItemSelected(item);
            }
        });

        return myView;
    }
}
