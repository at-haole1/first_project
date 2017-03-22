package com.example.kimhao.first_project.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kimhao.first_project.model.ItemUsers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KimHao on 17/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "at.db";
    public static final String CONTACTS_TABLE = "contacts";
    public static final String CONTACTS_COLUMN_IMAGE = "image";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_AGE = "age";
    private HashMap hp;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts ( id INTEGER primary key,image TEXT, name TEXT, age TEXT, address TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    // TODO: 17/03/2017
    //insert data Contact
    public boolean addContact(ItemUsers itemUsers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_IMAGE,itemUsers.getImgAva());
        contentValues.put(CONTACTS_COLUMN_NAME,itemUsers.getName());
        contentValues.put(CONTACTS_COLUMN_ADDRESS, itemUsers.getAddr());
        contentValues.put(CONTACTS_COLUMN_AGE, itemUsers.getAge());
        db.insert(CONTACTS_TABLE,null,contentValues);
        db.close();
        return true;
    }

    // TODO: 17/03/2017
    //returns data location in the table
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id= "+id+"",null);
        return res;
    }

    // TODO: 17/03/2017
    //update data table
    public boolean updateContact(ItemUsers itemUsers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_IMAGE,itemUsers.getImgAva());
        contentValues.put(CONTACTS_COLUMN_NAME,itemUsers.getName());
        contentValues.put(CONTACTS_COLUMN_AGE, itemUsers.getAge());
        contentValues.put(CONTACTS_COLUMN_ADDRESS,itemUsers.getAddr());
        db.update(CONTACTS_TABLE,contentValues,"id = ?",new String[]{String.valueOf(itemUsers.getId())});
        return  true;
    }

    //delete data table
     public Integer deleteContact (ItemUsers itemUsers){
         SQLiteDatabase db = this.getWritableDatabase();
         return db.delete(CONTACTS_TABLE,"id= ?",new String[]{String.valueOf(itemUsers.getId())});
     }

    public ArrayList<ItemUsers> getAllContacts(){
        ArrayList<ItemUsers> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contacts",null);

        if (cursor.moveToFirst()){
            do{
                ItemUsers itemUsers = new ItemUsers();
                itemUsers.setId(cursor.getString(0));
                itemUsers.setImgAva(cursor.getString(1));
                itemUsers.setName(cursor.getString(2));
                itemUsers.setAge(cursor.getString(3));
                itemUsers.setAddr(cursor.getString(4));

                array_list.add(itemUsers);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return array_list;
    }


}
