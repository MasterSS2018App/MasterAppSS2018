package com.hm_master.masterapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String Tag = "DatabaseHelper";

    public DatabaseHelper(Context context,String Table_Name){
        super(context,Table_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor GetData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        return null;
    }
}
