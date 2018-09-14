package com.ierp2.mrp.dao;


import com.ierp2.mrp.entity.Tenant;
import com.ierp2.mrp.configuration.jpa.PlatformJpaRepository;

public interface TenantDao extends PlatformJpaRepository<Tenant, Long> {
}
