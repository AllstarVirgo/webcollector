package com.potato.demo.dao.impl;

import com.potato.demo.domain.HuangRiver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HuangRiverRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        HuangRiver huangRiver=new HuangRiver();
        huangRiver.setRiverName(resultSet.getString("RIVER_NAME"));
        huangRiver.setLocalName(resultSet.getString("LOCAL_NAME"));
        huangRiver.setWaterLevel(resultSet.getDouble("WATER_LEVEL"));
        huangRiver.setRateOfFlow(resultSet.getDouble("RATE_OF_FLOW"));
        huangRiver.setSandContent(resultSet.getDouble("SAND_CONTENT"));
        huangRiver.setCurrentDate(resultSet.getDate("DOWNLOAD_DATE"));
        return huangRiver;
    }
}
