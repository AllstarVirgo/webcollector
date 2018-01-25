package com.potato.demo.dao;

import com.potato.demo.domain.SongliaoRainCondition;

import java.util.List;

public interface SongliaoRainConditionDao {
    public void insert(SongliaoRainCondition songliaoRainCondition);

    public void insertList(List<SongliaoRainCondition> list);
}
