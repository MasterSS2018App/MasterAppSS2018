package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Fragment_HM_Map extends Fragment {
    static View myView;

    RadioGroup radioGroup;
    RadioButton radioUG;
    RadioButton radioEG;
    RadioButton radioOG1;
    RadioButton radioOG2;
    RadioButton radioOG3;
    RadioButton radioOG4;

    ImageView hmMapUG;
    ImageView hmMapEG;
    ImageView hmMapOG1;
    ImageView hmMapOG2;
    ImageView hmMapOG3;
    ImageView hmMapOG4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (myView == null)
            myView = inflater.inflate(R.layout.layout_hm_map,container,false);

        hmMapUG = myView.findViewById(R.id.imageHmMap_UG);
        hmMapEG = myView.findViewById(R.id.imageHmMap_EG);
        hmMapOG1 = myView.findViewById(R.id.imageHmMap_OG1);
        hmMapOG2 = myView.findViewById(R.id.imageHmMap_OG2);
        hmMapOG3 = myView.findViewById(R.id.imageHmMap_OG3);
        hmMapOG4 = myView.findViewById(R.id.imageHmMap_OG4);


        radioGroup = myView.findViewById(R.id.RadioGroupHmMap);
        radioUG = (RadioButton) myView.findViewById(R.id.radioUG);
        radioEG = (RadioButton) myView.findViewById(R.id.radioEG);
        radioOG1 = (RadioButton) myView.findViewById(R.id.radioOG1);
        radioOG2 = (RadioButton) myView.findViewById(R.id.radioOG2);
        radioOG3 = (RadioButton) myView.findViewById(R.id.radioOG3);
        radioOG4 = (RadioButton) myView.findViewById(R.id.radioOG4);

        radioUG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });
        radioEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });
        radioOG1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });
        radioOG2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });
        radioOG3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });
        radioOG4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButttonWasClicked(v.getId());
            }
        });


        hmMapEG.setVisibility(GetVisibility(true));

        return myView;
    }

    private void RadioButttonWasClicked(int id) {
        radioUG.setChecked(id == radioUG.getId());
         radioEG.setChecked(id == radioEG.getId());
         radioOG1.setChecked(id == radioOG1.getId());
         radioOG2.setChecked(id == radioOG2.getId());
         radioOG3.setChecked(id == radioOG3.getId());
         radioOG4.setChecked(id == radioOG4.getId());

        hmMapUG = myView.findViewById(R.id.imageHmMap_UG);
        hmMapEG = myView.findViewById(R.id.imageHmMap_EG);
        hmMapOG1 = myView.findViewById(R.id.imageHmMap_OG1);
        hmMapOG2 = myView.findViewById(R.id.imageHmMap_OG2);
        hmMapOG3 = myView.findViewById(R.id.imageHmMap_OG3);
        hmMapOG4 = myView.findViewById(R.id.imageHmMap_OG4);

        hmMapUG.setVisibility(GetVisibility(id==radioUG.getId()));
        hmMapEG.setVisibility(GetVisibility(id==radioEG.getId()));
        hmMapOG1.setVisibility(GetVisibility(id==radioOG1.getId()));
        hmMapOG2.setVisibility(GetVisibility(id==radioOG2.getId()));
        hmMapOG3.setVisibility(GetVisibility(id==radioOG3.getId()));
        hmMapOG4.setVisibility(GetVisibility(id==radioOG4.getId()));


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private int GetVisibility(boolean visible){
        if(visible)
            return View.VISIBLE;
        else
            return View.GONE;
    }
}

