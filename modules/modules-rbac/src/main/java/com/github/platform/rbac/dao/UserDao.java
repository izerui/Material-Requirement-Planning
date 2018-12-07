package com.github.platform.rbac.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.User;

public interface UserDao extends PlatformJpaRepository<User, Long> {
}
