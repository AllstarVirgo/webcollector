package com.potato.demo.dao;

import com.potato.demo.domain.SongliaoRiver;

import java.util.List;

public interface SongliaoRiverDao {
    public void insert(SongliaoRiver songliaoRiver);

    public void insertList(List<SongliaoRiver> list);
}
