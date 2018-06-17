package com.hm_master.masterapp;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTableEntry {

    private static DatabaseHelper db = MainActivity.SqLiteDB;

    public static final String TABLE_NAME = "stundenplan";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_WEEKDAY = "wochentag";
    public static final String COLUMN_START = "von";
    public static final String COLUMN_END = "bis";
    public static final String COLUMN_KIND = "art";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                    + COLUMN_ROOM + " INTEGER NOT NULL,"
                    + COLUMN_WEEKDAY + " INTEGER NOT NULL,"
                    + COLUMN_START + " TEXT NOT NULL,"
                    + COLUMN_END + " TEXT NOT NULL,"
                    + COLUMN_KIND + " INTEGER NOT NULL"
                    + ")";

    private int DB_Id;
    private String Room;
    private int Kind;
    private int Weekday;

    private Date Start;
    private Date End;

    public TimeTableEntry() {

        DB_Id = 1;
        Room = "";
        Kind = R.integer.KindPcRoom;
        Start = new Date();
        End = new Date();
    }

    public TimeTableEntry(Cursor cursor) {


        DB_Id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        Room =  cursor.getString(cursor.getColumnIndex(COLUMN_ROOM));
        Kind =  cursor.getInt(cursor.getColumnIndex(COLUMN_KIND));
        Weekday = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEKDAY));
        String StartString =  cursor.getString(cursor.getColumnIndex(COLUMN_START));
        String EndString =  cursor.getString(cursor.getColumnIndex(COLUMN_END));
    }

    public ContentValues GetContentValue(){
        ContentValues daten = new ContentValues();

        return daten;
    }

    public static List<TimeTableEntry> GetEntriesOnWeekday(int weekday){

        List<TimeTableEntry> entries = new ArrayList<TimeTableEntry>();
        Cursor cursor;
        db.openDataBase();
        switch (weekday){
            case(R.integer.WeekDay_Monday):
                cursor = db.GetTableEntries(TABLE_NAME,"1",COLUMN_START);
                break;
            case(R.integer.WeekDay_Tuesday):
                cursor = db.GetTableEntries(TABLE_NAME,"2",COLUMN_START);
                break;
            case(R.integer.WeekDay_Wednesday):
                cursor = db.GetTableEntries(TABLE_NAME,"3",COLUMN_START);
                break;
            case(R.integer.WeekDay_Thursday):
                cursor = db.GetTableEntries(TABLE_NAME,"4",COLUMN_START);
                break;
            case(R.integer.WeekDay_Friday):
                cursor = db.GetTableEntries(TABLE_NAME,"5",COLUMN_START);
                break;
                default:
                    cursor = db.GetTableEntries(TABLE_NAME,null,COLUMN_START);
        }

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                entries.add(new TimeTableEntry(cursor));
            }
        }
        if(cursor != null && !cursor.isClosed())
            cursor.close();

        db.close();
        return  entries;
    }

    public String CreateSQLUpdate() {

        return null;
    }

    public String CreateSQLDelete() {

        return null;
    }

    public String CreateSQLNew() {

        return null;
    }

    public boolean IsFree(Date timeQ){

        return false;
    }

    public boolean WillBeFree(Date timeQ){

        return false;
    }

    public String CreateListEntry(){

        return "";
    }

}
