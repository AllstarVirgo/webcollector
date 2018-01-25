package com.potato.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentExtract {
    public static double extract(String string){
        String regex="\\d+(\\.)?(\\d+)?";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(string);
        if(m.find())return Double.parseDouble(m.group());
        else return -1;
    }
}
