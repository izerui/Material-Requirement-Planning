package com.github.platform.rbac.dao;


import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.Dept;

import java.util.List;

public interface DeptDao extends PlatformJpaRepository<Dept,Long> {
    List<Dept> findByTenantCode(String tenantCode);
}
