package com.github.platform.rbac.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.User;

import java.util.List;

public interface UserDao extends PlatformJpaRepository<User, Long> {
    List<User> findByTenantCode(String tenantCode);
    User findByUserCode(String userCode);
}
