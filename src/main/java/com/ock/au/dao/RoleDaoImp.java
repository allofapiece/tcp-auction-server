package com.ock.au.dao;

import com.ock.au.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("from Role");
        return query.getResultList();
    }
}
