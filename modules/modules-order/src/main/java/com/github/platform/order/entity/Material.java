package com.github.platform.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.platform.core.json.BigDecimalSerializer;
import com.github.platform.jpa.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "material")
public class Material extends BaseEntity {

    // 物料编号
    private String materiaCode;

    // 物料名称
    private String materialName;

    // 单位名称
    private String unitName;

    // 包装数量
    @Column(columnDefinition = "decimal(24,8) DEFAULT 0", nullable = false)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal packagingQty;

    // 单价
    @Column(columnDefinition = "decimal(24,8) DEFAULT 0", nullable = false)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitPrice = BigDecimal.ZERO;

}
