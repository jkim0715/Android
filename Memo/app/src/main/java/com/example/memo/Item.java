package com.example.p536;

import java.io.Serializable;

public class Item implements Serializable {
    String title;
    String date;
    String content;

    public Item(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
