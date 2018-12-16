package com.ock.au.dao;

import com.ock.au.entity.Lot;

import java.util.List;

public interface LotDao {
    void create(Lot user);
    List<Lot> findAll();
}
