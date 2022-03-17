package com.ems.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    public Date stringToDate(String date){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = null;

        try {
            date1 = format.parse(date);
        } catch (Exception e) {
            System.out.println(e);
        }

        return date1;
    }



}
