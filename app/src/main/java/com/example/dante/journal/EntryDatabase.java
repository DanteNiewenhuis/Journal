package com.example.dante.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    public EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String executable = "create table journal_entries (id INTEGER PRIMARY KEY, title TEXT, " +
                            "content TEXT, mood TEXT, timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(executable);

        //TODO create sample items to test
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String executable = "IF OBJECT_ID('journal_entries', 'U') IS NOT NULL DROP TABLE journal_entries";
        db.execSQL(executable);
        onCreate(db);
    }
}
