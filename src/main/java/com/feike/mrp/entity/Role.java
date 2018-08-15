package com.feike.mrp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//角色
@Data
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    //状态
    private int recordStatus;

    //账套编号
    private String entCode;

    //角色编号
    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String roleCode;

    //角色名称
    private String roleName;
}
