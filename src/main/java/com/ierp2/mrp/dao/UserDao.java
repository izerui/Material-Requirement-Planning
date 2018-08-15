package com.ierp2.mrp.dao;

import com.ierp2.mrp.entity.User;
import com.ierp2.mrp.support.jpa.PlatformJpaRepository;

public interface UserDao extends PlatformJpaRepository<User, Long> {
}
