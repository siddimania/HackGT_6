package com.example.vmac.WatBot;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivitySave extends SugarRecord<ActivitySave> {
    private String activityPerformed;
    private String todayDate;

    public ActivitySave() {}

    public ActivitySave(String activityPerformed) {
        this.activityPerformed = activityPerformed;
        int style = DateFormat.MEDIUM;
        DateFormat df;
        Date date = new Date();
        df = DateFormat.getDateInstance(style, Locale.JAPAN);
        this.todayDate = df.format(date);
    }

    public String getActivityPerformed() {
        return activityPerformed;
    }

    public String getAssignedDate() {
        return todayDate;
    }
}
