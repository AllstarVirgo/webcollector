package com.potato.demo.dao.impl;

import com.potato.demo.dao.SongliaoReserviorDao;
import com.potato.demo.domain.SongliaoReservoir;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class SongliaoReservoirDaoImpl extends JdbcDaoSupport implements SongliaoReserviorDao {
    @Override
    public void insertList(List<SongliaoReservoir> list) {
        String sql="INSERT INTO SONGLIAO_RESERVIOR "+
                "(RIVER_NAME,MONITORING_STATION_NAME,RELEASE_TIME,RESERVOIR_WATER_LEVEL,WATER_STORAGE_CAPACITY,OUTPUT_STORAGE)"+
                "VALUES (?,?,?,?,?,?)";
        for(SongliaoReservoir s:list){
            getJdbcTemplate().update(sql,new Object[]{s.getRiver_Name(),s.getMonitoring_Station_name(),s.getRelease_Time(),
            s.getReservoir_Water_Level(),s.getWater_Storage_Capacity(),s.getOutput_Storage()});
        }
    }
}
