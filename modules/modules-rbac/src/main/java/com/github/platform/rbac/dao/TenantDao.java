package com.github.platform.rbac.dao;


import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.Tenant;

public interface TenantDao extends PlatformJpaRepository<Tenant, Long> {
}
