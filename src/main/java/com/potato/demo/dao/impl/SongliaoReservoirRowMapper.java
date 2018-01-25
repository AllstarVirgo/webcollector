package com.potato.demo.dao.impl;

import com.potato.demo.domain.SongliaoReservoir;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongliaoReservoirRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        SongliaoReservoir songliaoReservoir=new SongliaoReservoir();
        songliaoReservoir.setRiver_Name(resultSet.getString("RIVER_NAME"));
        songliaoReservoir.setMonitoring_Station_name(resultSet.getString("MONITORING_STATION_NAME"));
        songliaoReservoir.setRelease_Time(resultSet.getTimestamp("RELEASE_TIME"));
        songliaoReservoir.setReservoir_Water_Level(resultSet.getDouble("RESERVOIR_WATER_LEVEL"));
        songliaoReservoir.setWater_Storage_Capacity(resultSet.getDouble("WATER_STORAGE_CAPACITY"));
        songliaoReservoir.setOutput_Storage(resultSet.getDouble("OUTPUT_STORAGE"));
        return songliaoReservoir;
    }
}
