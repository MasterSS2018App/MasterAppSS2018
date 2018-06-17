package com.hm_master.masterapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "timetableSS2018.db";
    private static String DATABASE_PATH;
    private Context mContext;

    private SQLiteDatabase db;


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);// 1? Its database Version
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.mContext = context;
    }

    public void createDataBase() throws IOException
    {
        //If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DATABASE_PATH + DATABASE_NAME;
        //Log.v("mPath", mPath);
        try {
            if(!checkDataBase())
                createDataBase();

            db = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //db  = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
            return db != null;
        }
        catch(Exception e)  {

        }
        return false;
    }



    @Override
    /**
     * Wird nur aufgerufen, wenn eine Datenbank neu erzeugt wird
     */
    public void onCreate(SQLiteDatabase db) {
        try {
            // Tabelle anlegen
            // create notes table
            //db.execSQL(TimeTableEntry.CREATE_TABLE);
            createDataBase();
        }
        catch(Exception ex) {
            Log.e("gehtfxs", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TimeTableEntry.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        if(db != null) {
            db.close();
            db = null;
        }

        super.close();
    }

    public Cursor GetTableEntries(String table,String selection,String orderBy){

        // get readable database as we are not inserting anything
        openDataBase();
        //db = this.getReadableDatabase();

        Cursor cursor;
        if(selection != null)
        cursor = db.query(table,
                null, selection, null, null, null, orderBy);
        else

            cursor = db.query(table,
                    null, null, null, null, null, null);

   //     close();
        return cursor;
    }
}