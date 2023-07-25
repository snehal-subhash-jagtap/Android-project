package com.example.androidproject.FycoHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;
import android.util.Log;

import androidx.annotation.Nullable;

public class FycoHelper extends SQLiteOpenHelper {


    public FycoHelper( Context context) {
        super(context,"Userdata.db",null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Userdetails(roll INTEGER primary key,name TEXT,percentage TEXT,contact TEXT,address TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists Userdetails");

    }

    public Boolean insertuserdata( Integer roll,String name,String percentage ,String  contact,String address){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("roll",roll);
        contentValues.put("name",name);
        contentValues.put("percentage",percentage);
        contentValues.put("contact",contact);
        contentValues.put("address",address);

        long result=db.insert("Userdetails",null,contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }

    }


    public Boolean updateuserdata( Integer roll,String name,String percentage ,String  contact,String address){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("roll",roll);
        contentValues.put("name",name);
        contentValues.put("percentage",percentage);
        contentValues.put("contact",contact);
        contentValues.put("address",address);
        Cursor cursor=db.rawQuery("Select * from Userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {

            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }


    public Boolean deleteuserdata( String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Userdetails where name=?", new String[]{name});
        if (cursor.getCount() > 0) {

            long result = db.delete("Userdetails",  "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getdata() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Userdetails",null);
        return cursor;

    }

}

