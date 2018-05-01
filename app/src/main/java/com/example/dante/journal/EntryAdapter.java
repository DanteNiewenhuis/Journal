package com.example.dante.journal;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;


public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("bindView", "get strings");
        String title = cursor.getString(1);
        String mood = cursor.getString(3);
        String timestamp = cursor.getString(4);

        Log.d("bindView", "put views");
        TextView titleView = view.findViewById(R.id.title);
        ImageView moodView = view.findViewById(R.id.mood_image);
        TextView timestampView = view.findViewById(R.id.date_and_time);

        Log.d("bindView", "put strings");
        titleView.setText(title);
        timestampView.setText(timestamp);
        int img_id = view.getResources().getIdentifier(mood, "drawable", context.getPackageName());
        moodView.setImageResource(img_id);
    }
}
