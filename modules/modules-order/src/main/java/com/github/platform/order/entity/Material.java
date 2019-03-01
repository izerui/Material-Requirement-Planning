package com.github.platform.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.platform.core.json.BigDecimalSerializer;
import com.github.platform.jpa.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 基础物料 隶属于租户下
 */
@Data
@Entity
@Table(name = "material")
public class Material extends BaseEntity {

    // 租户编号
    @Column(nullable = false, updatable = false)
    private String tenantCode;

    // 物料编号
    @Column(nullable = false, updatable = false, unique = true)
    protected String materiaCode;

    // 物料名称
    protected String materialName;

    // 单位名称
    protected String unitName;

    // 每个包装内物料的数量
    @Column(columnDefinition = "decimal(24,8) DEFAULT 0", nullable = false)
    @JsonSerialize(using = BigDecimalSerializer.class)
    protected BigDecimal packingQty;

    // 单价
    @Column(columnDefinition = "decimal(24,8) DEFAULT 0", nullable = false)
    @JsonSerialize(using = BigDecimalSerializer.class)
    protected BigDecimal unitPrice = BigDecimal.ZERO;

}
