package com.nusrat.childcare.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Constants for database and table
    public static final String DATABASE_NAME = "childcare.db";
    public static final String TABLE_NAME = "users";

    // Column names
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Type";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Password";

    // Constructor to initialize the database helper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Auto incrementing ID
                COL_2 + " TEXT, " +  // Name
                COL_3 + " TEXT NOT NULL, " +  // Type (e.g., parent, helper)
                COL_4 + " TEXT NOT NULL, " +  // Gmail, must be unique
                COL_5 + " TEXT NOT NULL);");  // Password field

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreate the table
        onCreate(db);

    }
}
