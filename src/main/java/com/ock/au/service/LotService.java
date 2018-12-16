package com.ock.au.service;

import com.ock.au.entity.Lot;

import java.util.List;

public interface LotService {
    void create(Lot user);
    List<Lot> findAll();
}
