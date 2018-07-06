package com.example.khaledmahmoud.medicindectionary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class DBHelper extends SQLiteOpenHelper implements Serializable{

    private static final String  DATABASE_NAME = "drugs.db";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* final String SQL_CREATE_DATABASE_TABLE = "CREATE TABLE "+
                DataContract.DataEntry.TABLE_NAME + " (" +
                DataContract.DataEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DataContract.DataEntry.COLUMN_DRUG_NAME +" TEXT NOT NULL,"+
                DataContract.DataEntry.COLUMN_DRUG_CATEGORY+" TEXT NOT NULL,"+
                DataContract.DataEntry.COLUMN_DRUG_DESCRIPTION+" TEXT NOT NULL"+");";
        db.execSQL(SQL_CREATE_DATABASE_TABLE);*/
       final String SQL_CREATE_DB = "CREATE TABLE "+
               DataContract.DataEntry.TABLE_NAME+"("+
               DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
               DataContract.DataEntry.COLUMN_DRUG_NAME+" TEXT NOT NULL,"+
               DataContract.DataEntry.COLUMN_DRUG_CATEGORY+" TEXT NOT NULL,"+
               DataContract.DataEntry.COLUMN_DRUG_DESCRIPTION+" TEXT NOT NULL"+");";
       db.execSQL(SQL_CREATE_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ DataContract.DataEntry.TABLE_NAME);
        onCreate(db);

    }

}
