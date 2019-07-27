package com.supercool.supercool_ipds_backend.common.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int calculateAge(Date date) {
       return getYear(new Date()) - getYear(date);
    }

    private static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

}
