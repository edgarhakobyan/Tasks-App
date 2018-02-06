package com.example.edgar.tasksapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edgar on 2/5/18.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Tasks.db";

    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("======= DATABASE CREATED ========");

        final String CREATE_TABLE = "CREATE TABLE " +
                TaskEntry.TABLE_NAME  + " (" +
                TaskEntry._ID         + " INTEGER PRIMARY KEY," +
                TaskEntry.TITLE       + " TEXT," +
                TaskEntry.DESCRIPTION + " TEXT," +
                TaskEntry.STATUS      + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("======= DATABASE DELETED ========");

        final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

}
