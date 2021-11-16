package com.dma.registrationloginwithsqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "apps.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table users(email TEXT primary key, password TEXT)");

        myDb.execSQL("create Table usersdetails(category_name TEXT, sub_category_name TEXT, name TEXT," +
                " phone TEXT primary key, address TEXT, date TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("drop Table if exists userdetails");
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = myDB.insert("users",null, contentValues);
        
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertUserData(String category_name, String sub_category_name, String name,
                                  String phone, String address, String date){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_name", category_name);
        contentValues.put("sub_category_name", sub_category_name);
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("date", date);

        long result = myDB.insert("usersdetails",null, contentValues);

        if (result == -1) return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usersdetails", null);
        return cursor;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where email = ?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkedEmailPassword(String email, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where email = ? and password = ? ", new String[] {email,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
