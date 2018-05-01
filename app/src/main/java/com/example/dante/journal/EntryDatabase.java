package com.example.dante.journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase instance;


    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("database", "constructor");
    }

    static EntryDatabase getInstance(Context context) {
        Log.d("database", "get instance");
        if (instance == null) {
            Log.d("database", "new database");
            instance = new EntryDatabase(context, "database", null, 1);

        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the table with the right columns
        String executable = "CREATE TABLE IF NOT EXISTS journal_entries(_id INTEGER PRIMARY KEY, title varchar(255), " +
                            "content varchar(255), mood varchar(255), timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(executable);
    }

    public void insert(JournalEntry entry) {
        String title = entry.getTitle();
        String content = entry.getContent();
        String mood = entry.getMood();

        String add = "INSERT INTO journal_entries(title, content, mood) ";
        add += "VALUES('" + title + "','" + content + "','" + mood + "');";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(add);
    }

    public void delete(long id) {
        String delete = "DELETE FROM journal_entries WHERE _id='" + id + "';";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(delete);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String executable = "DROP TABLE journal_entries";
        db.execSQL(executable);
        onCreate(db);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("database", "done with selectAll");
        return db.rawQuery("SELECT * FROM journal_entries", null);
    }

}
