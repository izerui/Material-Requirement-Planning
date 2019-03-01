package com.github.platform.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.platform.core.json.BigDecimalSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 报货订单 隶属于租户 关联租户下的部门 和 对应的客户 （针对物料）
 */
@Data
@Entity
@Table(name = "order")
public class Order extends Material{

    // 租户编号
    @Column(nullable = false, updatable = false)
    private String tenantCode;

    // 部门编号(该订单是哪个店的)
    @Column(nullable = false, updatable = false)
    private String deptCode;

    // 客户编号
    @Column(nullable = false, updatable = false, unique = true)
    private String customerCode;

    // 客户名称
    private String customerName;

    /**
     * 包装数
     */
    @Column(columnDefinition = "decimal(24,8) DEFAULT 0", nullable = false)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal packingNum;

}
