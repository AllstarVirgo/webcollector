package com.potato.demo.dao.impl;

import com.potato.demo.dao.SongliaoRiverDao;
import com.potato.demo.domain.SongliaoRiver;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class SongliaoRiverDaoImpl extends JdbcDaoSupport implements SongliaoRiverDao {
    @Override
    public void insert(SongliaoRiver songliaoRiver) {
        String sql="INSERT INTO SONGLIAO_RIVER "+
                "(RIVER_NAME,MONITORING_STATION_NAME,RELEASE_TIME,WATER_LEVEL,FLOW_RATE,WARNING_WATER_LEVEL) VALUES (?,?,?,?,?,?)";

        getJdbcTemplate().update(sql,new Object[]{songliaoRiver.getRiverName(),songliaoRiver.getMonitoringStationName(),
        songliaoRiver.getReleaseTime(),songliaoRiver.getWater_level(),songliaoRiver.getFlow_rate(),songliaoRiver.getWarning_water_level()});
    }

    @Override
    public void insertList(List<SongliaoRiver> list) {
        String sql="INSERT INTO SONGLIAO_RIVER "+
                "(RIVER_NAME,MONITORING_STATION_NAME,RELEASE_TIME,WATER_LEVEL,FLOW_RATE,WARNING_WATER_LEVEL) VALUES (?,?,?,?,?,?)";

        for (SongliaoRiver s :
                list) {
            getJdbcTemplate().update(sql,new Object[]{s.getRiverName(),s.getMonitoringStationName(),
                    s.getReleaseTime(),s.getWater_level(),s.getFlow_rate(),s.getWarning_water_level()});
        }
    }
}
