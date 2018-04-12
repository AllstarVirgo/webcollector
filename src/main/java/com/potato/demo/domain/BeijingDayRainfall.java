package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class BeijingDayRainfall {
    private String areaName;

    private String specificName;

    private Timestamp startTime;

    private Timestamp endTime;

    private double dayRainfall;

    public BeijingDayRainfall() {
    }

    public ArrayList<BeijingDayRainfall> analysisPage(Page page){
        ArrayList<BeijingDayRainfall>list=new ArrayList<BeijingDayRainfall>();
        Elements elements=page.select("tr");
        int n=elements.size();
        for (int i = 2; i < n-5; i++) {
            String[] strEles=elements.get(i).text().split(" ");
        }
        return list;
    }


}
