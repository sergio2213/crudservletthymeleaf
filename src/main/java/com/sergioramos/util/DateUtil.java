package com.sergioramos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static java.sql.Date toSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static Date toUtilDate(String dateInString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(dateInString);
    }
}
