package com.example.loginapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DatabaseName="AndroidProjects.db";
    static final int DBversion=1;

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(username TEXT PRIMARY KEY NOT NULL,email TEXT NOT NULL UNIQUE,password NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }
    public Boolean insertData(String username,String email,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        Long result=myDB.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkUsername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("Select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public  boolean checkusernamepassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("Select * from users where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean updatePassword(String username, String passwordNew) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", passwordNew);

        int rowsAffected = myDB.update("users", values, "username=?", new String[]{username});

        return rowsAffected > 0;
    }


}
