package com.ock.au.dao;

import com.ock.au.entity.Bet;
import com.ock.au.entity.Lot;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BetDaoImp implements BetDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Bet bet) {
        sessionFactory.getCurrentSession().save(bet);
    }

    @Override
    public List<Bet> findAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Bet> query = sessionFactory.getCurrentSession().createQuery("from Bet");
        return query.getResultList();
    }

}
