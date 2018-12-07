package com.github.platform.rbac.dao;


import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.Role;

public interface RoleDao extends PlatformJpaRepository<Role,Long> {
}
