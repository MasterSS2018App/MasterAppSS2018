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
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class Fragment_PCRoom_List extends Fragment {
    static View myView;

    public   final String FULL_DATE_FORMAT_STRING = "EEEE dd.MMMM yy HH:MM";
    public final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat(FULL_DATE_FORMAT_STRING, Locale.GERMANY);

    Calendar currentTime;

    TextClock textClock;
    TextClock textDate;

    List<TimeTableEntry> entries;
    Date selectedDate;
    int selectedWeekday;

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

        String selectedDateString = tday + " " + tdate + " " + time;

        try {
            selectedDate  = FULL_DATE_FORMAT.parse(selectedDateString);
        }
        catch (Exception ex){}

        textClock.setFormat12Hour(null);
        textClock.setText(time);
        textDate.setText(tday + " " + tdate);

        selectedWeekday = GetWeekdayId(selectedDate);
        entries = TimeTableEntry.GetEntriesOnWeekday(0);
        return myView;


    }

    private int GetWeekdayId(Date selectedDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(selectedDate); // yourdate is an object of type Date
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // this will for example return 3 for tuesday

        switch (dayOfWeek){
            case(1):
                return R.integer.WeekDay_Monday;
            case(2):
                return R.integer.WeekDay_Tuesday;
            case(3):
                return R.integer.WeekDay_Wednesday;
            case(4):
                return R.integer.WeekDay_Thursday;
            case(5):
                return R.integer.WeekDay_Friday;

                default:
                    return 0;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

