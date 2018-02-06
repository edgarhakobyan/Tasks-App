package com.example.edgar.tasksapp.db;

import android.provider.BaseColumns;

/**
 * Created by edgar on 2/6/18.
 */

public final class TaskEntry implements BaseColumns {
    public static final String TABLE_NAME = "tasks";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String STATUS = "status";
}
