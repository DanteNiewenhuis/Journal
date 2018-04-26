package com.example.dante.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private long id;
    private String title;
    private String content;
    private String mood;
    private String date_time;

    public JournalEntry(long id, String title, String content, String mood, String date_time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.date_time = date_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
