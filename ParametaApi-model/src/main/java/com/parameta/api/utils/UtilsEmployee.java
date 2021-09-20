package com.parameta.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilsEmployee {

    private static final String DATE_FORMAT = "MM-dd-yyyy";
    private static final int LIMIT_AGE = 18;

    public static boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Date generateDateFromStrDate(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.parse(dateStr);
    }


    public static boolean isLimitMayorAge(String dateStr) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate fechaNac = LocalDate.parse(dateStr, format);
        LocalDate now = LocalDate.now();
        Period period = Period.between(fechaNac, now);
        if (period.getYears() >= LIMIT_AGE) return true;
        else return false;
    }

}
