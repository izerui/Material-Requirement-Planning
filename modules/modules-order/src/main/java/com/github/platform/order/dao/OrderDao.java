package com.github.platform.order.dao;

import com.github.platform.jpa.PlatformJpaRepository;
import com.github.platform.order.entity.Order;

public interface OrderDao extends PlatformJpaRepository<Order,Long> {
}
