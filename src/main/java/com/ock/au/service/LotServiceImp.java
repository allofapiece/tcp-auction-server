package com.ock.au.service;

import com.ock.au.dao.LotDao;
import com.ock.au.entity.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class LotServiceImp implements LotService {

    @Autowired
    private LotDao lotDao;

    @Transactional
    @Override
    public void create(Lot lot) {
        lotDao.create(lot);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Lot> findAll() {
        return lotDao.findAll();
    }

}
