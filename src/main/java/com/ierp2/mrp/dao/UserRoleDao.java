package com.ierp2.mrp.dao;

import com.ierp2.mrp.entity.UserRole;
import com.ierp2.mrp.configuration.jpa.PlatformJpaRepository;

import java.util.List;

public interface UserRoleDao extends PlatformJpaRepository<UserRole,Long> {
    List<UserRole> findByTenantCodeAndUserCode(String tenantCode, String userCode);
}
