package com.ock.au.service;

import com.ock.au.dao.BetDao;
import com.ock.au.entity.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BetServiceImp implements BetService {

    @Autowired
    private BetDao betDao;

    @Transactional
    @Override
    public void create(Bet bet) {
        betDao.create(bet);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bet> findAll() {
        return betDao.findAll();
    }

}
