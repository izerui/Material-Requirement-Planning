package com.github.platform.order.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.order.entity.Customer;

public interface CustomerDao extends PlatformJpaRepository<Customer,Long> {
}
