package com.example.dante.journal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public int NEW_PROFILE = 1;
    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

        }
        else {
            FloatingActionButton new_profile_button = findViewById(R.id.new_profile_button);
            new_profile_button.setOnClickListener(new FloatingClickListener());
        }

        // TODO clean dit!!!!
        ListView journal_entries = findViewById(R.id.journal_entries);
        journal_entries.setOnItemClickListener(new ListItemClickListener());
        journal_entries.setOnItemLongClickListener(new ListItemLongClickListener());
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor all = db.selectAll();
        adapter = new EntryAdapter(getApplicationContext(), R.layout.entry_row, all, 0);
        journal_entries.setAdapter(adapter);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int scroll_pos = prefs.getInt("scroll", 0);

        Log.d("scroll pos", "pos = " + scroll_pos);
        journal_entries.setSelection(scroll_pos);
        journal_entries.setOnScrollListener(new ListScrollListener());
    }


    private class ListScrollListener implements AbsListView.OnScrollListener{

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
        int visibleItemCount, int totalItemCount) {
            Log.d("scrolling", "init");
            Log.d("scrolling", "item = " + firstVisibleItem);
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            editor.putInt("scroll", firstVisibleItem);

            editor.apply();
            Log.d("scrolling", "done");
        }
    }

    private void updateData() {
        Cursor all = db.selectAll();
        adapter.swapCursor(all);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private class FloatingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);

            startActivityForResult(intent, NEW_PROFILE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PROFILE) {
            if (data != null) {
                JournalEntry entry = (JournalEntry) data.getSerializableExtra("entry");
                if (entry != null) {
                    db.insert(entry);
                }
            }
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            intent.putExtra("entry", cursor_to_entry(cursor));

            startActivity(intent);
        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            long pos = cursor.getLong(0);
            db.delete(pos);
            updateData();
            return false;
        }
    }

    private JournalEntry cursor_to_entry(Cursor cursor) {
        long id = cursor.getLong(0);
        String title = cursor.getString(1);
        String content = cursor.getString(2);
        String mood = cursor.getString(3);
        String date_time = cursor.getString(4);

        return new JournalEntry(id, title, content, mood, date_time);
    }
}