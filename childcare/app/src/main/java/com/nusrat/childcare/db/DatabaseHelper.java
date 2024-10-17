package com.nusrat.childcare.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "childcare.db";
    public static final String TABLE_NAME = "users";


    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Type";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +  // Name
                COL_3 + " TEXT NOT NULL, " +
                COL_4 + " TEXT NOT NULL, " +
                COL_5 + " TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreate the table
        onCreate(db);

    }
}
