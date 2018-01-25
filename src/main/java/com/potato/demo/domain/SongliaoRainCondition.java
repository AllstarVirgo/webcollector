package com.potato.demo.domain;

import java.sql.Timestamp;

public class SongliaoRainCondition {
    private String drainageArea;

    private String riverName;

    private String stationName;

    private Timestamp releaseDate;

    private double dayRainfall;

    private String weather;

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
}
