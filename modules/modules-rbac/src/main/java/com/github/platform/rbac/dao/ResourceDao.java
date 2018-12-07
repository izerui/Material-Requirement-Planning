package com.github.platform.rbac.dao;


import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.rbac.entity.Resource;

public interface ResourceDao extends PlatformJpaRepository<Resource,Long> {
}
