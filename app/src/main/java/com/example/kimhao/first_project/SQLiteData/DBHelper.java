package com.example.kimhao.first_project.SQLiteData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

/**
 * Created by KimHao on 17/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userdatabase.db";
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_AGE = "age";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_IMAGE + " TEXT,"
                    + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " TEXT," + COLUMN_ADDRESS + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
