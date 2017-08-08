package com.example.subhampadhi.remarques;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by subhampadhi on 7/27/17.
 */

public class Notes implements Serializable {
    //serializable to save the state of the object
    private long mDateTime;
    private String mTitle;
    private String mContent;


    public Notes(long mDateTime, String mTitle, String mContent) {
        this.mDateTime = mDateTime;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    //Function Setting up Date and time on the app

    public String getDateTimeFormatted(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss",
                context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));

    }
}
