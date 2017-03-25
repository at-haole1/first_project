package com.example.kimhao.first_project.SQLiteData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.kimhao.first_project.Model.ItemUser;

import java.util.ArrayList;

import static android.os.Build.ID;

/**
 * Created by KimHao on 25/03/2017.
 */

public class DataBaseUser {
    public static final String TABLE_NAME = "User";
    public static final String USER_IMAGE = "image";
    public static final String USER_NAME = "name";
    public static final String USER_AGE = "age";
    public static final String USER_ADDRESS = "address";
    private DBHelper dbHandle;
    private Context mContext;

    public DataBaseUser(Context mContext) {
        this.mContext = mContext;
        dbHandle = new DBHelper(mContext);
    }

    public boolean insertUser(ItemUser user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_IMAGE, user.getImage());
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_AGE, user.getAge());
        contentValues.put(USER_ADDRESS, user.getAddress());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public ItemUser getUser(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where " + ID + " = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ItemUser user = new ItemUser(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return user;
    }

    public int numRows() {
        SQLiteDatabase db = dbHandle.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public int updateUser(ItemUser user) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_IMAGE, user.getImage());
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_AGE, user.getAge());
        contentValues.put(USER_ADDRESS, user.getAddress());
        return db.update(TABLE_NAME, contentValues, ID + " = ? ", new String[]{String.valueOf(user.getId())});
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + " = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<ItemUser> getAllUsers() {
        SQLiteDatabase db = dbHandle.getWritableDatabase();
        ArrayList<ItemUser> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ItemUser user = new ItemUser();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setImage(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setAge(cursor.getString(3));
                user.setAddress(cursor.getString(4));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }
}
