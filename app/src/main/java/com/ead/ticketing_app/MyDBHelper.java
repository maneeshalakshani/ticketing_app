package com.ead.ticketing_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    //defining db related variables
    private Context context;
    private static final String DATABASE_NAME = "ReservationLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_NIC = "nic";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_STATUS = "status";

    //constructor
    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //onCreate method for creating table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_USER_NAME+ " TEXT, "
                +COLUMN_NIC+ " TEXT, "
                +COLUMN_EMAIL+ " TEXT, "
                +COLUMN_PASSWORD+ " TEXT, "
                +COLUMN_STATUS+ " BOOLEAN);";

        sqLiteDatabase.execSQL(query);
    }

    //onUpgrade method for updating table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //add data to the database
    int addTicket(String name, String nic, String email, String password, boolean status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_NIC, nic);
        cv.put(COLUMN_STATUS, status);

        long result =  db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
            return -1;
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
            return 1;
        }
    }

    //get all data method
    Cursor readAllData() {
        String query = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //search user by nic
    public Cursor searchForUser(String nic){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME+ " WHERE " +COLUMN_NIC+ " = " +nic+ ";";
        return db.rawQuery(query, null);
    }
}
