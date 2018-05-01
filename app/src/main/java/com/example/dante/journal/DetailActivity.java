package com.example.dante.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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
        String date_time = entry.getDate_time();

        TextView title_text = findViewById(R.id.title_text);
        TextView date_time_text = findViewById(R.id.date_time_text);
        TextView content_text = findViewById(R.id.content_text);
        ImageView mood_image = findViewById(R.id.mood_image_detail);

        title_text.setText(title);
        content_text.setText(content);
        date_time_text.setText(date_time);

        int img_id = getResources().getIdentifier(mood, "drawable", getPackageName());
        mood_image.setImageResource(img_id);
    }
}
