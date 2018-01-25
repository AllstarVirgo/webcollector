package com.potato.demo.dao.impl;

import com.potato.demo.domain.SongliaoRiver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongliaoRiverRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        SongliaoRiver songliaoRiver=new SongliaoRiver();
        songliaoRiver.setRiverName(resultSet.getString("RIVER_NAME"));
        songliaoRiver.setMonitoringStationName(resultSet.getString("MONITORING_STATION_NAME"));
        songliaoRiver.setReleaseTime(resultSet.getTimestamp("RELEASE_TIME"));
        songliaoRiver.setWater_level(resultSet.getDouble("WATER_LEVEL"));
        songliaoRiver.setFlow_rate(resultSet.getDouble("FLOW_RATE"));
        songliaoRiver.setWarning_water_level(resultSet.getDouble("WARNING_WATER_LEVEL"));
        return songliaoRiver;
    }
}
