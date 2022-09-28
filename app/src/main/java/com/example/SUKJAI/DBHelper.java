package com.example.SUKJAI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Register.db";
    private static final String TABLE_NAME = "USERS";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "Surname";
    private static final String COL_4 = "Username";
    private static final String COL_5 = "Password";
    private static final String COL_6 = "Email";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT ,Surname TEXT ,Username TEXT , Password TEXT , Email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(MyDB);

    }

    public boolean registerUser(String username,String password,String name,String mail,String Surname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, Surname);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, password);
        contentValues.put(COL_6, mail);

        long result = MyDB.insert(TABLE_NAME,null,contentValues);
        if(result == -1) return false;
        else return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String[] {username});
        if (cursor.getCount() > 0) {return true;}
        else {return  false;}
    }

    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[] {username,password});
        if (cursor.getCount() > 0 ) {return true;}
        else {return  false;}
    }

    public Cursor getAllDate (String name,String surname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor res = MyDB.rawQuery("Select * from name where name = ? and surname",new String[] {name,surname});
        return res;
    }


//    public boolean create (Account account){
//        boolean result = true;
//        try {
//            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(COL_2, account.ge);
//            contentValues.put(COL_3, account.getsurname());
//            contentValues.put(COL_4, username);
//            contentValues.put(COL_5, password);
//            contentValues.put(COL_6, mail);
//        }catch (Exception e){
//            result = false;
//        }
//    }



}

