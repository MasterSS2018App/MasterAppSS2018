package com.hm_master.masterapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Fragment_PCRoom_List extends Fragment {
    static View myView;

    Calendar currentTime;

    TextClock textClock;
    TextClock textDate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(myView == null)
            myView = inflater.inflate(R.layout.layout_pcroom_list,container,false);


        currentTime = Calendar.getInstance();
        String time = new SimpleDateFormat("hh:mm", Locale.GERMANY).format(currentTime.getTime());
        String tdate = new SimpleDateFormat("dd.MMMM yy", Locale.GERMANY).format(currentTime.getTime());
        String tday  = new SimpleDateFormat("EEEE",Locale.GERMANY).format(currentTime.getTime());

        textClock = myView.findViewById(R.id.clockPC);
        textDate = myView.findViewById(R.id.datePC);

        textClock.setFormat12Hour(null);
        textClock.setText(time);
        textDate.setText(tday + " " + tdate);
        return myView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

