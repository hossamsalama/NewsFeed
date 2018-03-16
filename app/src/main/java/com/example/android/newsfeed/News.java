package com.example.android.newsfeed;

/**
 * Created by hossam on 6/12/2017.
 *
 */

class News {
    private String mTitle;
    private String mDate;
    private String mSectionName;

    public News(String title, String date, String sectionName){
        mTitle = title;
        mDate = date;
        mSectionName = sectionName;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDate(){
        return mDate;
    }

    public String getSectionName(){
        return mSectionName;
    }
}
