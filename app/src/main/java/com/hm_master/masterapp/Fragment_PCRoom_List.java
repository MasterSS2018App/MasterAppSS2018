package com.hm_master.masterapp;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class Fragment_PCRoom_List extends Fragment {
    static View myView;

    Calendar currentTime;

    TextView textClock;
    TextView textDate;

    List<TimeTableEntry> entries;
    Date selectedDate;

    public static Fragment_PCRoom_List Instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(myView == null)
            myView = inflater.inflate(R.layout.layout_pcroom_list,container,false);

        Instance = this;
        ListView lstFreeRooms = myView.findViewById(R.id.lst_pc);


        currentTime = Calendar.getInstance();

        textClock = myView.findViewById(R.id.clockPC);
        textDate = myView.findViewById(R.id.datePC);

        //selectedDate  = currentTime.getTime();

         // Test Tag 19.06.2018

        String selectedDateString = "19.06.2018 10:37";
        SimpleDateFormat testFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            selectedDate  = testFormat.parse(selectedDateString);
        }
        catch (Exception ex){}

        String time = TimeTableEntry.FULL_CLOCK_FORMAT.format(selectedDate);
        String tdate = TimeTableEntry.FULL_DATE_FORMAT.format(selectedDate);

        textClock.setText(time);
        textDate.setText(tdate);


       CustomListAdapter adapter =
               new CustomListAdapter(MainActivity.Instance,Room.GetRooms(R.integer.KindPcRoom ,selectedDate));
       lstFreeRooms.setAdapter(adapter);

        return myView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void Refresh() {

        ListView lstFreeRooms = myView.findViewById(R.id.lst_pc);

        CustomListAdapter adapter =
                new CustomListAdapter(MainActivity.Instance,Room.GetRooms(R.integer.KindPcRoom ,selectedDate));
        lstFreeRooms.setAdapter(adapter);
    }
}



