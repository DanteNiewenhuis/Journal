package com.example.dante.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry(View v) {
        TextView title_input = findViewById(R.id.title_input);
        TextView content_input = findViewById(R.id.content_input);
        TextView mood_input = findViewById(R.id.mood_input);

        String title = title_input.getText().toString();
        String content = content_input.getText().toString();
        String mood = mood_input.getText().toString();

        Intent intent = new Intent();
        JournalEntry entry = new JournalEntry(0, title, content, mood, "");

        intent.putExtra("entry", entry);

        setResult(1, intent);
        finish();
    }
}
