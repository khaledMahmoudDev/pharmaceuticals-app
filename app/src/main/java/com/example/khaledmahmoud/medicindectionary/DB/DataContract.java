package com.example.khaledmahmoud.medicindectionary.DB;

import android.provider.BaseColumns;

public  class DataContract {


    public static final class DataEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "drugsDB";
        public static final String COLUMN_DRUG_NAME = "drugName";
        public static final String COLUMN_DRUG_CATEGORY = "drugCategory";
        public static final String COLUMN_DRUG_DESCRIPTION = "drugDescription";


    }

}
