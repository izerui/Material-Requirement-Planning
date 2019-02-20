package com.github.platform.rbac.service;

import com.github.platform.jpa.base.Conditions;
import com.github.platform.rbac.dao.*;
import com.github.platform.rbac.entity.Dept;
import com.github.platform.rbac.entity.Resource;
import com.github.platform.rbac.entity.Role;
import com.github.platform.rbac.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RbacService {


    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    DeptDao deptDao;

    public User getUserByUserCode(String userCode) {
        return userDao.findOne(Conditions.where("userCode").is(userCode));
    }

    public List<Resource> findResources() {
        return resourceDao.findAll();
    }

    public List<User> findUsers() {
        return userDao.findAll();
    }

    public List<Role> findRoles() {
        return roleDao.findAll();
    }

    public List<Dept> findDepts() {
        return deptDao.findAll();
    }

}
