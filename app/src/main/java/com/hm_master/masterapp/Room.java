package com.hm_master.masterapp;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Room {

    static Date mySelectedDate;

    String myName;
    int myRoomID;
    int myKind;
    TimeTableEntry nextEntrie;
    boolean isFree;

    private Room(String name,int kind,int id){
        myKind = kind;
        myName = name;
        myRoomID = id;
    }

    @Override
    public String toString() {
        return myName;
    }

    public String getStatus(){
        if(isFree)
            return "Frei";
        else
            return "Belegt";
    }


    public String getName(){return myName;}

    public String getDuration(){
        if(nextEntrie == null)
            return MainActivity.Instance.getResources().getString(R.string.entry_duration_string_noMoreEvents);
        return nextEntrie.getDuration(isFree,mySelectedDate);
    }

    public String getTime() {
        if(nextEntrie == null)
            return MainActivity.Instance.getResources().getString(R.string.entry_time_string_noMoreEvents);
        String timeStart = new SimpleDateFormat("HH:mm", Locale.GERMANY).format(nextEntrie.getStart().getTime());
        String timeEnd = new SimpleDateFormat("HH:mm", Locale.GERMANY).format(nextEntrie.getEnd().getTime());
        if (isFree)
            return MainActivity.Instance.getResources().getString(R.string.entry_time_free, timeStart);
        else
            return MainActivity.Instance.getResources().getString(R.string.entry_time_future, timeEnd);
    }

    public static List<Room> GetRooms(int kind, Date date){
        mySelectedDate = date;
        List<Room> rooms = new ArrayList<Room>();
        List<Room> freeRooms = new ArrayList<Room>();
        List<Room> futureRooms = new ArrayList<Room>();

        List<TimeTableEntry> entries = TimeTableEntry.GetEntriesOnWeekday(date,kind);

        switch (kind){
            case (R.integer.KindClassRoom):

                rooms.add(new Room("HS 21",kind,R.string.Room_HS21));
                rooms.add(new Room("HS 115",kind,R.string.Room_HS115));
                rooms.add(new Room("HS 222",kind,R.string.Room_HS222));

                break;

            case (R.integer.KindPcRoom):
                default:

                rooms.add(new Room("DV 6",kind,R.string.Room_DV6));
                rooms.add(new Room("DV 7",kind,R.string.Room_DV7));
                rooms.add(new Room("DV 8",kind,R.string.Room_DV8));

                break;
        }

        for (Room room:rooms) {

            if(room.isFreeNow(date,entries)){
                freeRooms.add(room);
            }else if(room.WiilBeFree(date,entries))
                futureRooms.add(room);
        }

        freeRooms.addAll(futureRooms);
        return freeRooms;
    }

    private boolean isFreeNow(Date date,List<TimeTableEntry> timeTableEntries){

        isFree = true;

        for (TimeTableEntry timeEntry:timeTableEntries) {
            // Falscher Raum
            if(timeEntry.getRoomID() != myRoomID)
                continue;


            // Startet erst in der Zukunft
            if(DateToDouble(timeEntry.getStart())>DateToDouble(date)) {
                if(nextEntrie == null)
                    nextEntrie = timeEntry;
                continue;
            }

            // Stunde schon vobei
            if(DateToDouble(timeEntry.getEnd())<=DateToDouble(date))
                continue;

            isFree = false;
        }

        return isFree;
    }

    private boolean WiilBeFree(Date date,List<TimeTableEntry> timeTableEntries){

        for (TimeTableEntry timeEntry:timeTableEntries) {
            // Falscher Raum
            if(timeEntry.getRoomID() != myRoomID)
                continue;

            // Stunde schon vobei
            if(DateToDouble(timeEntry.getEnd())<DateToDouble(date))
                continue;

            if(nextEntrie == null){
                nextEntrie = timeEntry;
                continue;
            }

            if(DateToDouble(timeEntry.getStart()) == DateToDouble(nextEntrie.getEnd()))
                nextEntrie = timeEntry;

        }

        return true;
    }

    private double DateToDouble(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        double hour = cal.get(Calendar.HOUR_OF_DAY);
        double minutes = cal.get(Calendar.MINUTE);
        double time = hour + minutes/60;
        return  time;
    }
}
