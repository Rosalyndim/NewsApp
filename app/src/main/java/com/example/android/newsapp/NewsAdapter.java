package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Lyn on 24/07/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the news at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view SectionNews
        TextView sectionView = (TextView) listItemView.findViewById(R.id.sectionName);
        String sectionName = currentNews.getSection();
        // Display the section of the current news in that TextView
        sectionView.setText(sectionName);

        TextView titleView = (TextView) listItemView.findViewById(R.id.webTitle);
        String webTitle = currentNews.getTitle();
        // Display the title of the current news in that TextView
        titleView.setText(webTitle);

        TextView urlView = (TextView) listItemView.findViewById(R.id.webUrl);
        String webUrl = currentNews.getUrl();
        // Display the url of the current news in that TextView
        urlView.setText(webUrl);

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String date = currentNews.getDate();
        dateView.setText(date);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


}
