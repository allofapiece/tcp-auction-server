package com.ock.au.dao;

import com.ock.au.entity.Bet;

import java.util.List;

public interface BetDao {
    void create(Bet bet);

    List<Bet> findAll();
}
