package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.potato.demo.utils.DateConvert;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SongliaoRainCondition {
    private String drainageArea;

    private String riverName;

    private String stationName;

    private Timestamp releaseDate;

    private double dayRainfall;

    private String weather;

    public SongliaoRainCondition() {
    }

    public SongliaoRainCondition(String drainageArea, String riverName, String stationName, Timestamp releaseDate, double dayRainfall, String weather) {
        this.drainageArea = drainageArea;
        this.riverName = riverName;
        this.stationName = stationName;
        this.releaseDate = releaseDate;
        this.dayRainfall = dayRainfall;
        this.weather = weather;
    }

    public String getDrainageArea() {
        return drainageArea;
    }

    public String getRiverName() {
        return riverName;
    }

    public String getStationName() {
        return stationName;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public double getDayRainfall() {
        return dayRainfall;
    }

    public String getWeather() {
        return weather;
    }

    public void setDrainageArea(String drainageArea) {
        this.drainageArea = drainageArea;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDayRainfall(double dayRainfall) {
        this.dayRainfall = dayRainfall;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "SongliaoRainCondition{" +
                "drainageArea='" + drainageArea + '\'' +
                ", riverName='" + riverName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", releaseDate=" + releaseDate +
                ", dayRainfall=" + dayRainfall +
                ", weather='" + weather + '\'' +
                '}';
    }

    public ArrayList<SongliaoRainCondition> analysisPage(Page page){
        ArrayList<SongliaoRainCondition> list=new ArrayList<SongliaoRainCondition>();

        Elements contents=page.select("tr");

        int n=contents.size();
        for (int i = 11; i <n ; i++) {
            Element e=contents.get(i);
            String[] records=e.text().split(" ");
            Timestamp sqlTime= DateConvert.convertStrtoDate(records[3]+" "+records[4]);

            //解析日雨量
            double dayRainfall;
            String dayRainfallStr=records[5];
            if (dayRainfallStr.startsWith(".")){
                dayRainfallStr="0"+dayRainfallStr;
                dayRainfall=Double.parseDouble(dayRainfallStr);
            }else dayRainfall=Double.parseDouble(dayRainfallStr);

            list.add(new SongliaoRainCondition(records[0],records[1],records[2],sqlTime,dayRainfall,records[6]));
        }

        return list;
    }
}
