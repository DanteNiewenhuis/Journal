package com.example.dante.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("entry");

        String title = entry.getTitle();
        String mood = entry.getMood();
        String content = entry.getContent();

        TextView title_text = findViewById(R.id.title_text);
        // TextView date_time_text = findViewById(R.id.date_time_text); TODO fix date time
        TextView mood_text = findViewById(R.id.mood_text);
        TextView content_text = findViewById(R.id.content_text);

        title_text.setText(title);
        mood_text.setText(mood);
        content_text.setText(content);

    }
}
