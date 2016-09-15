package com.comli.dawnbreaksthrough.personalintro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.comli.dawnbreaksthrough.personalintro.Elements.Databases;

/**
 * Created by SparklingGT on 9/14/2016.
 */
public class DatabaseCreator extends SQLiteOpenHelper
{
    private static final int INT_DATABASES_VERSION = 1; // for practice purpose only
    private static final String STRING_DATABASES_NAME = "personTable.db";

    public DatabaseCreator(Context context) {
        super(context, STRING_DATABASES_NAME, null, INT_DATABASES_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Databases.PersonTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                Databases.PersonTable.Cols.UUID + ", " +
                Databases.PersonTable.Cols.NAME + ", " +
                Databases.PersonTable.Cols.GENDER + ", " +
                Databases.PersonTable.Cols.BIRTH + ", " +
                Databases.PersonTable.Cols.INTRO + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //not a requirement for homework
    }
}
