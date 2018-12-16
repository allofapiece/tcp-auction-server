package com.ock.au.dao;

import com.ock.au.entity.Lot;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LotDaoImp implements LotDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Lot lot) {
        sessionFactory.getCurrentSession().save(lot);
    }

    @Override
    public List<Lot> findAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Lot> query = sessionFactory.getCurrentSession().createQuery("from Lot");
        return query.getResultList();
    }

}
