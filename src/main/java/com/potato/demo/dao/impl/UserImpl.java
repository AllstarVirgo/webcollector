package com.potato.demo.dao.impl;

import com.potato.demo.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserImpl {
    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    private UserDao userDao= (UserDao) context.getBean("userDao");

    public String getPassword(String name){
        return userDao.selectByName(name);
    }
}
