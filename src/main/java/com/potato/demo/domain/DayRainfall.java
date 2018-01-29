package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.potato.demo.utils.DateConvert;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by AllstarVirgo on 2018/1/28.
 */
public class DayRainfall {
    private String localName;

    private Timestamp startTime;

    private Timestamp endTime;

    private double sumRainfall;

    public DayRainfall() {
    }

    public DayRainfall(String localName, Timestamp startTime, Timestamp endTime, double sumRainfall) {
        this.localName = localName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sumRainfall = sumRainfall;
    }

    public String getLocalName() {
        return localName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public double getSumRainfall() {
        return sumRainfall;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setSumRainfall(double sumRainfall) {
        this.sumRainfall = sumRainfall;
    }

    public ArrayList<DayRainfall> analysisPage(Page page){
        ArrayList<DayRainfall> list=new ArrayList<DayRainfall>();

        Elements contents=page.select("tr");

        int n=contents.size();
        for (int i = 11; i <n ; i++) {
            Element e = contents.get(i);
            String[] records = e.text().split(" ");
            Timestamp startTime= DateConvert.convertStrtoDate(records[1]+" "+records[2]);
            Timestamp endTime= DateConvert.convertStrtoDate(records[3]+" "+records[4]);
            double sumDayRainfall=Double.parseDouble(records[5]);
            list.add(new DayRainfall(records[0],startTime,endTime,sumDayRainfall));
        }
        return list;
    }
}
