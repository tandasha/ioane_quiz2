package com.example.ioanetandashvili_quiz_2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Db";
    public static final String TABLE = "Json";

    public static final String KEY_ID = "_id";
    public static final String KEY_User_ID = "user_id";
    public static final String KEY_Title = "title";
    public static final String KEY_Comp = "completed";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + "(" + KEY_ID
                + " integer primary key,"+ KEY_User_ID + " text," + KEY_Title + " text," + KEY_Comp + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);

        onCreate(db);

    }
}
