package com.example.admin.fragment8;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "saveLesson";
    public static final String TABLE_SAVE = "mySaveLesson";
    public static final String KEY_ID = "_id";
    public static final String KEY_GROUP = "groupp";
    public static final String KEY_DAY = "day";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_TEACHER2 = "teacher2";
    public static final String KEY_STUDY = "study";
    public static final String KEY_NUMERATOR = "numerator";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_SAVE + "(" + KEY_ID + " integer primary key,"
                + KEY_GROUP + " text,"
                + KEY_DAY + " text,"
                + KEY_NUMBER + " text,"
                + KEY_NAME + " text,"
                + KEY_TEACHER + " text,"
                + KEY_TEACHER2 + " text,"
                + KEY_STUDY + " text,"
                + KEY_NUMERATOR + " text" + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SAVE);
        onCreate(db);
    }
}
