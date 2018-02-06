package com.example.edgar.tasksapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edgar.tasksapp.Task;

import java.util.ArrayList;

/**
 * Created by edgar on 2/6/18.
 */

public class TaskProvider {

    private static TaskDbHelper mTaskDbHelper;

    private TaskProvider(Context context) {
        mTaskDbHelper = new TaskDbHelper(context);
    }

    public static TaskDbHelper getTaskProvider(Context context) {
        if (mTaskDbHelper == null) {
            new TaskProvider(context);
        }
        return mTaskDbHelper;
    }


    public static void insertTaskIntoDb(SQLiteDatabase db, String title, String description) {
        System.out.println("======= Insert into db ========");
        //SQLiteDatabase dbWrite = mTaskDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TaskEntry.TITLE, title);
        values.put(TaskEntry.DESCRIPTION, description);
        values.put(TaskEntry.STATUS, "To Do");

        long newRowId = db.insert(TaskEntry.TABLE_NAME, null, values);

        System.out.println("======= newRowId: " + newRowId);
    }

    public static ArrayList<Task> getAllDataFromDb(SQLiteDatabase db) {
        System.out.println("======= Get db ========");
        //SQLiteDatabase dbRead = mTaskDbHelper.getReadableDatabase();

        // Define sections which will be used after query
        String[] sections = {
                TaskEntry._ID,
                TaskEntry.TITLE,
                TaskEntry.DESCRIPTION,
                TaskEntry.STATUS
        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = TaskEntry.TITLE + " = ?";
        //String[] selectionArgs = { "Edgar" };

        //To sort results
        //String sortOrder = TaskEntry.TITLE + " DESC";

        Cursor cursor = db.query(
                TaskEntry.TABLE_NAME,             // The table to query
                sections,                          // The columns to return
                null,                        // The columns for the WHERE clause
                null,                     // The values for the WHERE clause
                null,                         // don't group the rows
                null,                          // don't filter by row groups
                null                             // The sort order
        );

        ArrayList<Task> tasks = new ArrayList<Task>();
        while(cursor.moveToNext()) {
            tasks.add(new Task(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();

        return tasks;
    }

    public static void updateDb(SQLiteDatabase db, String id, String status) {
        System.out.println("======= Update db ========");
        //SQLiteDatabase db = mTaskDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(TaskEntry.STATUS, status);

        // Which row to update, based on the title
        String selection = TaskEntry._ID + " = ?";
        String[] selectionArgs = { id };

        int count = db.update(
                TaskEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        System.out.println("======= count: " + count);
    }
}
