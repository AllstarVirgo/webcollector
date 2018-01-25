package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.potato.demo.utils.ContentExtract;
import com.potato.demo.utils.DateConvert;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SongliaoReservoir {
    private String river_Name;

    private String monitoring_Station_name;

    private Timestamp release_Time;

    private double reservoir_Water_Level;

    private double water_Storage_Capacity;

    private double output_Storage;

    public SongliaoReservoir() {
    }

    public SongliaoReservoir(String river_Name, String monitoring_Station_name, Timestamp release_Time, double reservoir_Water_Level, double water_Storage_Capacity, double output_Storage) {
        this.river_Name = river_Name;
        this.monitoring_Station_name = monitoring_Station_name;
        this.release_Time = release_Time;
        this.reservoir_Water_Level = reservoir_Water_Level;
        this.water_Storage_Capacity = water_Storage_Capacity;
        this.output_Storage = output_Storage;
    }

    public String getRiver_Name() {
        return river_Name;
    }

    public String getMonitoring_Station_name() {
        return monitoring_Station_name;
    }

    public Timestamp getRelease_Time() {
        return release_Time;
    }

    public double getReservoir_Water_Level() {
        return reservoir_Water_Level;
    }

    public double getWater_Storage_Capacity() {
        return water_Storage_Capacity;
    }

    public double getOutput_Storage() {
        return output_Storage;
    }

    public void setRiver_Name(String river_Name) {
        this.river_Name = river_Name;
    }

    public void setMonitoring_Station_name(String monitoring_Station_name) {
        this.monitoring_Station_name = monitoring_Station_name;
    }

    public void setRelease_Time(Timestamp release_Time) {
        this.release_Time = release_Time;
    }

    public void setReservoir_Water_Level(double reservoir_Water_Level) {
        this.reservoir_Water_Level = reservoir_Water_Level;
    }

    public void setWater_Storage_Capacity(double water_Storage_Capacity) {
        this.water_Storage_Capacity = water_Storage_Capacity;
    }

    public void setOutput_Storage(double output_Storage) {
        this.output_Storage = output_Storage;
    }

    public ArrayList<SongliaoReservoir> analysisPage(Page page){
        ArrayList<SongliaoReservoir>list=new ArrayList<SongliaoReservoir>();

        Elements elements=page.select("tr");
        int n=elements.size();
        for (int i = 11; i <n ; i++) {
            String[] records=elements.get(i).text().split(" ");
            Timestamp sqlDate= DateConvert.convertStrtoDate(records[2]+" "+records[3]);
            double waterLevel;
            if(!records[4].equals("--"))waterLevel= ContentExtract.extract(records[4]);
            else waterLevel=-1;
            double waterStorageCapacity;
            if(!records[5].equals("--"))waterStorageCapacity=ContentExtract.extract(records[5]);
            else waterStorageCapacity=-1;
            double outputStorage;
            if(!records[6].equals("--"))outputStorage=ContentExtract.extract(records[6]);
            else outputStorage=-1;
            list.add(new SongliaoReservoir(records[0],records[1],sqlDate,waterLevel,waterStorageCapacity,outputStorage));
        }
        return list;
    }
}
