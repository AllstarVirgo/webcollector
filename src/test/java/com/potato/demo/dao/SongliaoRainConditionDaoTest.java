package com.potato.demo.dao;

import com.potato.demo.domain.SongliaoRainCondition;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class SongliaoRainConditionDaoTest {
    @Test
    public void insertTest(){
        SqlSession sqlSession=getSessionFactory().openSession();
        SongliaoRainConditionDao s=sqlSession.getMapper(SongliaoRainConditionDao.class);
        SongliaoRainCondition sRC=new SongliaoRainCondition();
        sRC.setDrainageArea("松江");
        sRC.setRiverName("黄河");
        sRC.setDayRainfall(12.3);
        sRC.setStationName("珠江口");
        sRC.setWeather("晴");
        s.insert(sRC);
        System.out.println(s.toString());
    }

    private static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sqlSessionFactory=null;
        String res="myBatisConf.xml";
        try {
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }


}