package com.potato.demo.utils;

import com.potato.demo.dao.SongliaoRainConditionDao;
import com.potato.demo.domain.SongliaoRainCondition;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        insertTest();
    }
    public static void insertTest(){
        //设置为自动提交
        SqlSession sqlSession=getSessionFactory().openSession(true);
        SongliaoRainConditionDao s=sqlSession.getMapper(SongliaoRainConditionDao.class);
        SongliaoRainCondition sRC=new SongliaoRainCondition();
        sRC.setDrainageArea("松江");
        sRC.setRiverName("黄河");
        sRC.setDayRainfall(12.3);
        sRC.setStationName("珠江口");
        sRC.setWeather("晴");
        s.insert(sRC);
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
