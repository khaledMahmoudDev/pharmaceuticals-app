package com.example.khaledmahmoud.medicindectionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.example.khaledmahmoud.medicindectionary.DB.DBHelper;
import com.example.khaledmahmoud.medicindectionary.DB.DBInstructions;
import com.example.khaledmahmoud.medicindectionary.data_model.Drug;

public class AddItem extends AppCompatActivity {
    private EditText name;
    private EditText category;
    private EditText description;
    private Drug drug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        name = (EditText)findViewById(R.id.add_name);
        category = (EditText)findViewById(R.id.add_cat);
        description = (EditText)findViewById(R.id.add_desc);


    }

    public void addRow(View view)
    {
        if (name.getText().length() == 0||
                category.getText().length()== 0||
                description.getText().length() == 0)
        {
            return;
        }
        drug = new Drug(null,name.getText().toString(),category.getText().toString(),description.getText().toString());



      //  dbInstructions.createDB();
       // dbInstructions.close();
        Intent intent = new Intent(AddItem.this,MainActivity.class);
        intent.putExtra("newDrug",drug);
        startActivity(intent);
        finish();
    }
}
