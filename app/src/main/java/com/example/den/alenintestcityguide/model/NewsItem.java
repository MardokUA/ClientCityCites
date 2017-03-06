package com.example.den.alenintestcityguide.model;

/**
 * Created by den on 2017-03-06.
 */

public class NewsItem {
    private String title;
    private String text;
    private Boolean important;
    private String date;
    private String URL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", important=" + important +
                ", date='" + date + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
