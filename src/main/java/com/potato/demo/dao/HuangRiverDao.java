package com.potato.demo.dao;


import com.potato.demo.domain.HuangRiver;

import java.util.List;

public interface HuangRiverDao {
    public void insert(HuangRiver huangRiver);

    public void insertList(List<HuangRiver> list);

    public HuangRiver findByStationName(String localName);
}
