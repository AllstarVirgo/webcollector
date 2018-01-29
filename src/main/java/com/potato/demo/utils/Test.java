package com.potato.demo.utils;

import com.potato.demo.dao.SongliaoRainConditionDao;
import com.potato.demo.domain.SongliaoRainCondition;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        insertTest();
    }
    public static void insertTest(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
        SongliaoRainConditionDao s= (SongliaoRainConditionDao) ac.getBean("songliaoRainConditionDao");

        ArrayList<SongliaoRainCondition>list=new ArrayList<SongliaoRainCondition>();
        SongliaoRainCondition sRC=new SongliaoRainCondition();
        sRC.setDrainageArea("松江");
        sRC.setRiverName("黄河");
        sRC.setDayRainfall(12.3);
        sRC.setStationName("珠江口");
        sRC.setWeather("晴");
        list.add(sRC);
        System.out.println(list.size());
        s.insertList(list);
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
