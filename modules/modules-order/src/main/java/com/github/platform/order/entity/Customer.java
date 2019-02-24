package com.github.platform.order.entity;

import com.github.platform.jpa.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    // 客户编号
    private String customerCode;

    //客户名字
    private String customerName;

    //客户手机号
    private String customerPhone;

    // 微信openId
    private String wxOpenId;


}
