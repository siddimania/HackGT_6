package com.example.vmac.WatBot;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BillPaid extends SugarRecord<ActivitySave> {
    private String paidBill;
    private String currentMonth;

    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    public BillPaid() {
    }

    public BillPaid(String paidBill) {
        this.paidBill = paidBill;
        Calendar cal = Calendar.getInstance();
        this.currentMonth = monthName[cal.get(Calendar.MONTH)];
    }

    public String getPaidBill() {
        return paidBill;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }
}
