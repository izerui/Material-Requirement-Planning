package com.github.platform.rbac.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends PlatformJpaRepository<UserRole,Long> {
    List<UserRole> findByTenantCodeAndUserCode(String tenantCode, String userCode);
}
