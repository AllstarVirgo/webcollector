package com.potato.demo.utils;

import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ContentExtractTest {

    @Test
    public void extractDate() {
        String s = "2017年12月13日湖北省武汉市降雨量为3.72毫米";
        Date date=new Date();
        ContentExtract.extractDate(s, date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(date);
    }
}