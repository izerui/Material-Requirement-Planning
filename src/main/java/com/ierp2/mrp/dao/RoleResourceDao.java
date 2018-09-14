package com.ierp2.mrp.dao;

import com.ierp2.mrp.entity.RoleResource;
import com.ierp2.mrp.configuration.jpa.PlatformJpaRepository;

import java.util.List;

public interface RoleResourceDao extends PlatformJpaRepository<RoleResource,Long> {
    List<RoleResource> findByTenantCodeAndRoleCode(String tenantCode, String roleCode);
}
