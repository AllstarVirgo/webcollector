package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.potato.demo.utils.DateConvert;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongliaoRiver {
    private String riverName;

    private String monitoringStationName;

    private Timestamp releaseTime;

    private double water_level;

    private double flow_rate;

    private double warning_water_level;

    public SongliaoRiver() {
    }

    public SongliaoRiver(String riverName, String monitoringStationName, Timestamp releaseTime, double water_level, double flow_rate, double warning_water_level) {
        this.riverName = riverName;
        this.monitoringStationName = monitoringStationName;
        this.releaseTime = releaseTime;
        this.water_level = water_level;
        this.flow_rate = flow_rate;
        this.warning_water_level = warning_water_level;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public void setMonitoringStationName(String monitoringStationName) {
        this.monitoringStationName = monitoringStationName;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setWater_level(double water_level) {
        this.water_level = water_level;
    }

    public void setFlow_rate(double flow_rate) {
        this.flow_rate = flow_rate;
    }

    public void setWarning_water_level(double warning_water_level) {
        this.warning_water_level = warning_water_level;
    }

    public String getRiverName() {
        return riverName;
    }

    public String getMonitoringStationName() {
        return monitoringStationName;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public double getWater_level() {
        return water_level;
    }

    public double getFlow_rate() {
        return flow_rate;
    }

    public double getWarning_water_level() {
        return warning_water_level;
    }

    public ArrayList<SongliaoRiver> analysisPage(Page page) throws ParseException {
        ArrayList<SongliaoRiver>list=new ArrayList<SongliaoRiver>();

        Elements contents=page.select("tr");

        int n=contents.size();

        for (int i = 11; i <n ; i++) {
            org.jsoup.nodes.Element e=contents.get(i);
            String[] records=e.text().split(" ");
            //解析出时间
            Timestamp sqlDate= DateConvert.convertStrtoDate(records[2]+" "+records[3]);
            //解析警戒水位
            String record2=e.child(5).child(0).toString();
            Pattern p=Pattern.compile("\\d+(\\.)?\\d+");
            Matcher m;
            m=p.matcher(record2);
            double warningWaterLevel;
            if(m.find())warningWaterLevel= Double.parseDouble(m.group());
            else warningWaterLevel=-1;
            //解析水位和流量
            double waterLevel;
            if(!records[4].equals("--")) {
                m=p.matcher(records[4]);
                if(m.find()) {
                    waterLevel = Double.parseDouble(m.group());
                }else continue;
            }
            else waterLevel=-1;

            double flowRate;
            String flowRateStr=e.child(3).child(0).toString();
            m=p.matcher(flowRateStr);
            if(m.find())flowRate=Double.parseDouble(m.group());
            else flowRate=-1;
            list.add(new SongliaoRiver(records[0],records[1],sqlDate, waterLevel,flowRate,warningWaterLevel));
        }

        return list;
    }
}
