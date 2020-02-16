package com.gabisoft.darabszam_widget;

import android.media.TimedMetaData;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Darabszam {
    private Calendar calendar;
    private int darab;
    private String time;

    Darabszam() {
        this.update();
    }


    public void update() {
        this.calendar = Calendar.getInstance();
        this.darab = 0;
        //this.time = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) +":"+ Integer.toString(calendar.get(Calendar.MINUTE));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        this.time = simpleDateFormat.format(calendar.getTime());
        int hour = this.calendar.get(Calendar.HOUR_OF_DAY);
        int min = this.calendar.get(Calendar.MINUTE);
        if (hour >= 6 && hour < 18) {
            hour -=6;

        } else if (hour >= 18) {
            hour -= 18;
        } else {
            hour += 6;
        }
        //Log.d("PRINT","Time: " + this.time);
        //Log.d("PRINT","Óra: " + Integer.toString(hour));
        if(hour == 0) {
            this.darab= (int)((float)min/(60.0f/40.0f));
        } else if(hour == 11) {
            this.darab= (int)((float)min/(60.0f/40.0f))+480;
        } else {
            this.darab= (int)((float)min/(60.0f/44.0f))+hour*44-4;
        }
        //Log.d("PRINT","Darab :" + Integer.toString(this.darab));
    }

    public String getDarab() {
        return Integer.toString(darab);
    }

    public String getTime() {
        return time;
    }
}
