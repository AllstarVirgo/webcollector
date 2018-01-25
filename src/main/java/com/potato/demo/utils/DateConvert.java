package com.potato.demo.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvert {
    public static Timestamp convertStrtoDate(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm");
        java.util.Date release_time= null;
        try {
            release_time = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp sqlDate=new Timestamp(release_time.getTime());
        return sqlDate;
    }
}
