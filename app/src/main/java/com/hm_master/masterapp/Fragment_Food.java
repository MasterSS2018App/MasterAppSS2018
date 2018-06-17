package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Food extends Fragment {
    @Nullable

    View myView;

    Button btnK6;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_food,container,false);
        MainActivity.Instance.getSupportActionBar().setTitle(R.string.nav_Food);

        btnK6 = myView.findViewById(R.id.btn_k6);
        btnK6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                MainActivity.Instance.ChangeFragment(R.integer.food_k6);
            }
        });
        
        return myView;
    }
}
