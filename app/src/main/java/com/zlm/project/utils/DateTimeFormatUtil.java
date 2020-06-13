package com.zlm.project.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Milla
 * @create 2019/4/23
 */
public final class DateTimeFormatUtil {

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    // -------------------------------------------
    private DateTimeFormatUtil() {
    }

    // -------------------------------------------

    /**
     * parse 0001-01-01T00:00:00 to specified format.
     *
     * @param date      date string
     * @param formatStr specified format
     */
    public static String paresDate(String date, String formatStr) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    // -------------------------------------------
    public static String getUTC() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }

    // -------------------------------------------
}
