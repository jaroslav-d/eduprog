package com.example.jaroslav.itmodbep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaroslav.itmodbep.EPDatabaseSchema.EPTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.StudentTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.LecturerTable;
import com.example.jaroslav.itmodbep.EPDatabaseSchema.SPTable;

public class EPBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "EPBase.db";

    public EPBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EPTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                EPTable.Cols.NAME_PROGRAM + ", " +
                EPTable.Cols.ID_EDUCATION_GROUP + "," +
                EPTable.Cols.NUMBER_BP + ", " +
                EPTable.Cols.NUMBER_CP + ", " +
                EPTable.Cols.PROGRAM_MANAGER + ", " +
                EPTable.Cols.CONTACT_PERSON + ", " +
                EPTable.Cols.ENTRANCE_TESTS +
                ")"
        );
        db.execSQL("create table " + StudentTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                StudentTable.Cols.NAME_STUDENT + "," +
                StudentTable.Cols.ID_EDUCATION_GROUP + "," +
                StudentTable.Cols.ID_SCIENCE_PROJECT + "," +
                StudentTable.Cols.BIRTH_DAY +
                ")"
        );
        db.execSQL("create table " + LecturerTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                LecturerTable.Cols.NAME_LECTURER + "," +
                LecturerTable.Cols.ID_SCIENCE_PROJECT + "," +
                LecturerTable.Cols.POSITION +
                ")"
        );
        db.execSQL("create table " + SPTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SPTable.Cols.NAME_PROJECT + "," +
                SPTable.Cols.ID_SCIENCE_PROJECT + "," +
                SPTable.Cols.DESCRIPTION +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
