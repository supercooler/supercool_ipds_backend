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

    public static Date calculateDate(int age) {
        return new Date((getYear(new Date())-age-1970+1)*31536000000L);
    }

}
