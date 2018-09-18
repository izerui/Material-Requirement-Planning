package com.ierp2.mrp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "dept")
public class Dept extends BaseEntity {
    //部门编号
    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String deptCode;

    //部门名称
    private String deptName;

    //排序
    private int sort;

    //状态：0禁用 1正常
    private int recordStatus;

    //账套
    private String tenantCode;

}
