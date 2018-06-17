package com.hm_master.masterapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimeTableEntry {


    public static String FULL_DATE_FORMAT_STRING = "EEEE dd.MMMM yy HH:MM";
    public static SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat(FULL_DATE_FORMAT_STRING, Locale.GERMANY);

    public static String TIME_FORMAT_STRING = "HHMM";
    public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT_STRING, Locale.GERMANY);

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
    private int Room;
    private int Kind;
    private int Weekday;

    private Date Start;
    private Date End;

    public TimeTableEntry(Cursor cursor) {


        DB_Id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

        Kind = cursor.getInt(cursor.getColumnIndex(COLUMN_KIND));
        Weekday = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEKDAY));
        String StartString = cursor.getString(cursor.getColumnIndex(COLUMN_START));
        String EndString = cursor.getString(cursor.getColumnIndex(COLUMN_END));

        try {
            Start = TimeTableEntry.TIME_FORMAT.parse(StartString);
            End = TimeTableEntry.TIME_FORMAT.parse(EndString);
        } catch (Exception ex) {
            Start = null;
            End = null;
        }

        String RoomString = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM));
        Room = RoomStringToRoom(RoomString);

    }

    public int getDB_Id() {
        return DB_Id;
    }

    public String getRoom() {
        return RoomToRoomString();
    }

    public int getKind() {
        return Kind;
    }

    public int getRoomID(){return Room;}

    public Date getStart() {
        return Start;
    }

    public Date getEnd() {return End; }


    public String getDuration(boolean isFree,Date date){
        Calendar calcNow = Calendar.getInstance();
        calcNow.setTime(date);
        double HourNow = calcNow.get(Calendar.HOUR_OF_DAY);
        double MinNow = calcNow.get(Calendar.MINUTE);
        double timeNow = HourNow + MinNow/60;

        Calendar cal = Calendar.getInstance();
        cal.setTime(Start);
        double HourNext = cal.get(Calendar.HOUR_OF_DAY);
        double MinNext = cal.get(Calendar.MINUTE);
        double timeNext = HourNext + MinNext/60;

        double calcDura = timeNext - timeNow;

        int elapsedHours = (int) calcDura;
        int elapsedMinutes = (int) ((calcDura - elapsedHours) *60);

        String duration;

        if(isFree) {
            if (elapsedHours < 1) {
                duration = MainActivity.Instance.getResources().getString(R.string.entry_duration_min, elapsedMinutes);
            } else {
                duration = MainActivity.Instance.getResources().getString(R.string.entry_duration_std_min, elapsedHours, elapsedMinutes);
            }
        }
        else
        {
            if (elapsedHours < 1) {
                duration = MainActivity.Instance.getResources().getString(R.string.entry_duration_min_future, elapsedMinutes*-1);
            } else {
                duration = MainActivity.Instance.getResources().getString(R.string.entry_duration_std_min_future, elapsedHours*-1, elapsedMinutes*-1);
            }
        }
    return  duration;
    }

    public static Cursor GetCursorOnWeekday(Date date, int kind) {
        String kindInDb;
        switch (kind) {
            case (R.integer.KindPcRoom):
                kindInDb = "1";
                break;
            case (R.integer.KindClassRoom):
                kindInDb = "2";
                break;
            default:
                kindInDb = "1";
        }
        int selectedWeekday = GetWeekdayId(date);

        String selection_Column = COLUMN_WEEKDAY + "=? AND " + COLUMN_KIND + "=?";

        String[] selection_Value;

        db.openDataBase();

        switch (selectedWeekday) {
            case (R.integer.WeekDay_Monday):
                selection_Value = new String[]{"1",kindInDb};
                break;
            case (R.integer.WeekDay_Tuesday):
                selection_Value = new String[]{"2",kindInDb};
                break;
            case (R.integer.WeekDay_Wednesday):
                selection_Value = new String[]{"3",kindInDb};
                break;
            case (R.integer.WeekDay_Thursday):
                selection_Value = new String[]{"4",kindInDb};
                break;
            case (R.integer.WeekDay_Friday):
                selection_Value = new String[]{"5",kindInDb};
                break;
            default:
                return db.GetTableEntries(TABLE_NAME, null,null, COLUMN_START);
        }
        return db.GetTableEntries(TABLE_NAME, selection_Column,selection_Value, COLUMN_START);
    }

    private int RoomStringToRoom(String roomString) {
        switch (roomString.toLowerCase()) {
            case ("dv6"):
                return R.string.Room_DV6;
            case ("dv7"):
                return R.string.Room_DV7;
            case ("dv8"):
                return R.string.Room_DV8;
            case ("hs21"):
                return R.string.Room_HS21;
            case ("hs115"):
                return R.string.Room_HS115;
            case ("hs222"):
                return R.string.Room_HS222;
            default:
                return R.string.Room_HS222;
        }
    }

    private String RoomToRoomString() {
        switch (Room) {
            case (R.string.Room_DV6):
                return "DV 6";
            case (R.string.Room_DV7):
                return "DV 7";
            case (R.string.Room_DV8):
                return "DV 8";
            case (R.string.Room_HS21):
                return "HS 21";
            case (R.string.Room_HS115):
                return "HS 115";
            case (R.string.Room_HS222):
                return "HS 222";
            default:
                switch (Kind) {
                    case (1):
                        return "DV 6";
                    case (2):
                        return "HS 21";
                    default:
                        return "DV 6";
                }
        }
    }

    public static List<TimeTableEntry> GetEntriesOnWeekday(Date date,int kind) {

        Cursor cursor = GetCursorOnWeekday(date,kind);
        List<TimeTableEntry> entries = new ArrayList<TimeTableEntry>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                entries.add(new TimeTableEntry(cursor));
            }
        }

        if (!cursor.isClosed())
            cursor.close();

        return entries;
    }

    private static int GetWeekdayId(Date selectedDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(selectedDate); // yourdate is an object of type Date
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // this will for example return 3 for tuesday

        switch (dayOfWeek) {
            case (1):
                return R.integer.WeekDay_Monday;
            case (2):
                return R.integer.WeekDay_Tuesday;
            case (3):
                return R.integer.WeekDay_Wednesday;
            case (4):
                return R.integer.WeekDay_Thursday;
            case (5):
                return R.integer.WeekDay_Friday;

            default:
                return 0;
        }
    }

    public String CreateSQLDelete() {

        return null;
    }

    public String CreateSQLNew() {

        return null;
    }

    public boolean IsFree(Date timeQ) {

        return false;
    }

    public boolean WillBeFree(Date timeQ) {

        return false;
    }

    public String CreateListEntry() {

        return "";
    }
}
