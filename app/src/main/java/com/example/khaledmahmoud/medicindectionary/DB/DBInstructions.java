package com.example.khaledmahmoud.medicindectionary.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.khaledmahmoud.medicindectionary.data_model.Drug;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DBInstructions implements Serializable {
    private static Context context = null;
    private static DBHelper dbHelper;
    private static SQLiteDatabase sqLiteDatabase;
    
    public DBInstructions(Context context)
    {
        this.context = context;
    }


    public void createDB()
    {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

    }
    public void close()
    {
        dbHelper.close();
    }


    public void addNewGuest(String name,String cat, String desc) {

        ContentValues cv = new ContentValues();
        cv.put(DataContract.DataEntry.COLUMN_DRUG_DESCRIPTION,desc);
        cv.put(DataContract.DataEntry.COLUMN_DRUG_CATEGORY,cat);
        cv.put(DataContract.DataEntry.COLUMN_DRUG_NAME,name);


        sqLiteDatabase.beginTransaction();
       // String ins = "INSERT INTO drugs(drugDescription,drugCategory,drugName) VALUES ("+desc+","+cat+","+name+")";
       //  sqLiteDatabase.execSQL(ins);
        Long id = sqLiteDatabase.insert(DataContract.DataEntry.TABLE_NAME,null,cv);
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
     //   Toast.makeText(context,"id "+id,Toast.LENGTH_LONG).show();
    }

    public List<Drug> getAllDrugs ()
    {
        List<Drug> drugs = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(DataContract.DataEntry.TABLE_NAME
        ,null
        ,null
        ,null
        ,null
        ,null
        ,null);

        if (cursor.moveToFirst()) {
            do {
                Drug drug = new Drug();
                drug.setId(cursor.getLong(cursor.getColumnIndex(DataContract.DataEntry._ID)));
                drug.setName(cursor.getString(cursor.getColumnIndex(DataContract.DataEntry.COLUMN_DRUG_NAME)));
                drug.setCategory(cursor.getString(cursor.getColumnIndex(DataContract.DataEntry.COLUMN_DRUG_CATEGORY)));
                drug.setDescription(cursor.getString(cursor.getColumnIndex(DataContract.DataEntry.COLUMN_DRUG_DESCRIPTION)));

                drugs.add(drug);

            } while (cursor.moveToNext());

        }

        return drugs;
    }

    public boolean deleteDrug(Long id)
    {
        int i = sqLiteDatabase.delete(DataContract.DataEntry.TABLE_NAME, DataContract.DataEntry._ID+"="+id,null);
        if (i > 0 )
        {
            return true;
        }
        else return false;

    }
}
