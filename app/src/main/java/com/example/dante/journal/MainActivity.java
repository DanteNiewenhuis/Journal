package com.example.dante.journal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private int NEW_PROFILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

        }
        else {
            FloatingActionButton new_profile_button = findViewById(R.id.new_profile_button);
            new_profile_button.setOnClickListener(new FloatingClickListener());

            ListView journal_entries = findViewById(R.id.journal_entries);
            journal_entries.setOnItemClickListener(new ListItemClickListener());
            journal_entries.setOnItemLongClickListener(new ListItemLongClickListener());
        }

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
                //TODO handle data
            }
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return true;
        }
    }
}
