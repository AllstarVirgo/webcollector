package com.potato.demo.domain;


import java.sql.Date;

public class HubeiDayRainfall {
    private String provinceName;

    private String cityName;

    private String countryName;

    private double rainfall;

    private Date rainDate;

    public HubeiDayRainfall() {
    }

    public HubeiDayRainfall(String provinceName, String cityName, String countryName, double rainfall, Date rainDate) {
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.rainfall = rainfall;
        this.rainDate = rainDate;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public void setRainDate(Date rainDate) {
        this.rainDate = rainDate;
    }

    public boolean notNull(){
        return cityName!=null&&countryName!=null&&rainfall!=-1&& rainDate !=null;
    }

    public void reset(){
        provinceName=null;
        cityName=null;
        countryName=null;
        rainfall=-1;
        rainDate=null;
    }
}
