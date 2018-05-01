package com.example.dante.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        RadioGroup mood_input = findViewById(R.id.mood_input);
        mood_input.setOnCheckedChangeListener(new RadioCheckedChangedListener());
    }

    public void addEntry(View v) {
        TextView title_input = findViewById(R.id.title_input);
        TextView content_input = findViewById(R.id.content_input);
        RadioGroup mood_input = findViewById(R.id.mood_input);
        int checked_id = mood_input.getCheckedRadioButtonId();
        RadioButton checked = findViewById(checked_id);

        String title = title_input.getText().toString();
        String content = content_input.getText().toString();
        String mood = checked.getText().toString();

        Intent intent = new Intent();
        JournalEntry entry = new JournalEntry(0, title, content, mood, "");

        intent.putExtra("entry", entry);

        setResult(1, intent);
        finish();
    }

    private class RadioCheckedChangedListener  implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton checkedRadioButton = group.findViewById(checkedId);

            boolean isChecked = checkedRadioButton.isChecked();

            if (isChecked)
            {
                String img_name = checkedRadioButton.getText().toString();
                ImageView image = findViewById(R.id.mood_image);
                int img_id = getResources().getIdentifier(img_name, "drawable", getPackageName());
                image.setImageResource(img_id);
            }
        }



    }
}
