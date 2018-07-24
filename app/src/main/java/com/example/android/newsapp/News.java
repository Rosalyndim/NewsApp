package com.example.android.newsapp;

/**
 * Created by Lyn on 24/07/2018.
 */

public class News {

    private String mUrl;
    private String mTitle;
    private String mSection;
    private String mDate;

    public News(String url, String title, String section, String date) {
        mUrl = url;
        mTitle = title;
        mSection = section;
        mDate = date;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() { return mSection; }

    public String getDate() {
        return mDate;
    }
}
