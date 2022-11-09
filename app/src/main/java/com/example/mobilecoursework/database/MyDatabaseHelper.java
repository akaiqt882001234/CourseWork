package com.example.mobilecoursework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "My_Database.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_management";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "text_name";
    private static final String COLUMN_DESTINATION = "text_destination";
    private static final String COLUMN_DATE = "text_date";
    private static final String COLUMN_RISK = "text_risk";
    private static final String COLUMN_DESCRIPTION = "text_description";
    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, "+ COLUMN_DESTINATION + " TEXT, "+ COLUMN_DATE + " TEXT, "+ COLUMN_RISK + " TEXT, "+ COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void add_trip(String name, String destination, String description, String risk, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISK, risk);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null, cv);
/*        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }*/

    }

}

