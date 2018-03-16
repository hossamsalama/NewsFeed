package com.example.android.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by hossam on 6/12/2017.
 *
 */

public class NewsFeedAdapter extends ArrayAdapter<News>{

    public NewsFeedAdapter(Activity context, List<News> newsList){
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_news, parent, false);
        }

        News currentHeadline = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentHeadline.getTitle());

        TextView publicationDate = (TextView) listItemView.findViewById(R.id.publication_date);
        publicationDate.setText(currentHeadline.getDate());

        TextView sectionName = (TextView)listItemView.findViewById(R.id.section_name);
        sectionName.setText(currentHeadline.getSectionName());

        return listItemView;
    }
}
