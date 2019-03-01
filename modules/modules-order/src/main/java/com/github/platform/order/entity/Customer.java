package com.github.platform.order.entity;

import com.github.platform.jpa.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 客户 隶属于租户下
 */
@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    // 租户编号
    @Column(nullable = false, updatable = false)
    private String tenantCode;

    // 客户编号
    @Column(nullable = false, updatable = false, unique = true)
    private String customerCode;

    //客户名字
    private String customerName;

    //客户手机号
    private String customerPhone;

    // 微信openId
    @Column(nullable = false, updatable = false, unique = true)
    private String wxOpenId;


}
