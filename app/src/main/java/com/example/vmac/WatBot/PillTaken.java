package com.example.vmac.WatBot;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PillTaken extends SugarRecord<ActivitySave> {
    private String pillTaken;
    private String todayDate;

    public PillTaken() {
    }

    public PillTaken(String pillTaken) {
        this.pillTaken = pillTaken;
        int style = DateFormat.MEDIUM;
        DateFormat df;
        Date date = new Date();
        df = DateFormat.getDateInstance(style, Locale.JAPAN);
        this.todayDate = df.format(date);
    }

    public String getPillTaken() {
        return pillTaken;
    }

    public String getAssignedDate() {
        return todayDate;
    }
}
