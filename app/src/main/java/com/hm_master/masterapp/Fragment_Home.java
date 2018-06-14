package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import static android.view.View.*;


public class Fragment_Home extends Fragment{
    @Nullable

    View myView;
    ImageButton btnPcRoom;
    ImageButton btnClass;
    ImageButton btnLib;
    ImageButton btnFood;
    ImageButton btnMVV;
    ImageButton btnFun;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.layout_home, container, false);

        btnPcRoom = myView.findViewById(R.id.btn_pcRoom);
        btnPcRoom.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_PcRooms);
            }
        });

        btnClass = myView.findViewById(R.id.btn_classroom);
        btnClass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_Classroom);
            }
        });

        btnLib = myView.findViewById(R.id.btnLib);
        btnLib.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_Library);
            }
        });

        btnFood = myView.findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_Food);
            }
        });

        btnMVV = myView.findViewById(R.id.btnMvv);
        btnMVV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_Mvv);
            }
        });

        btnFun = myView.findViewById(R.id.btnFun);
        btnFun.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MainActivity.Instance.ChangeFragment(R.string.nav_Fun);
            }
        });


        return myView;
    }


}
