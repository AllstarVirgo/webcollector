package com.potato.demo.dao.impl;

import com.potato.demo.dao.HuangRiverDao;
import com.potato.demo.domain.HuangRiver;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class HuangRiverDaoImpl extends JdbcDaoSupport implements HuangRiverDao {

    @Override
    public void insert(HuangRiver huangRiver) {
        String sql="INSERT INTO HUANGRIVER "+
                "(RIVER_NAME,LOCAL_NAME,WATER_LEVEL,RATE_OF_FLOW,SAND_CONTENT,DOWNLOAD_DATE) VALUES (?,?,?,?,?,?)";


        getJdbcTemplate().update(sql,new Object[]{huangRiver.getRiverName(),huangRiver.getLocalName(),
                    huangRiver.getWaterLevel(),huangRiver.getRateOfFlow(),
                huangRiver.getSandContent(),huangRiver.getCurrentDate()});
    }

    @Override
    public void insertList(List<HuangRiver> list) {
        String sql="INSERT INTO HUANGRIVER "+
                "(RIVER_NAME,LOCAL_NAME,WATER_LEVEL,RATE_OF_FLOW,SAND_CONTENT,DOWNLOAD_DATE) VALUES (?,?,?,?,?,?)";

        for(HuangRiver huangRiver:list){
            getJdbcTemplate().update(sql,new Object[]{huangRiver.getRiverName(),huangRiver.getLocalName(),
                    huangRiver.getWaterLevel(),huangRiver.getRateOfFlow(),
                    huangRiver.getSandContent(),huangRiver.getCurrentDate()});
        }
    }

    @Override
    public HuangRiver findByStationName(String localName) {
        String sql="SELECT * FROM HUANGRIVER WHERE LOCAL_NAME = ?";

        HuangRiver huangRiver= (HuangRiver) getJdbcTemplate().queryForObject(
                sql,new Object[]{localName},new HuangRiverRowMapper());

        return huangRiver;
    }
}
