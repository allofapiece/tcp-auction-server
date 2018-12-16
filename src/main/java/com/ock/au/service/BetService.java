package com.ock.au.service;

import com.ock.au.entity.Bet;

import java.util.List;

public interface BetService {
    void create(Bet bet);
    List<Bet> findAll();
}
