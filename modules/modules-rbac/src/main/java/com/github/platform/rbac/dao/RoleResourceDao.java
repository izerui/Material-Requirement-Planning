package com.github.platform.rbac.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.RoleResource;

import java.util.List;

public interface RoleResourceDao extends PlatformJpaRepository<RoleResource,Long> {
    List<RoleResource> findByTenantCodeAndRoleCode(String tenantCode, String roleCode);
}
