package com.ock.au.service;

import com.ock.au.dao.RoleDao;
import com.ock.au.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

}
