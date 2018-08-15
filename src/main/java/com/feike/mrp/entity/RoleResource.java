package com.feike.mrp.entity;

import lombok.Data;

import javax.persistence.*;

//角色-资源关系
@Data
@Entity
@Table(name = "role_resource")
public class RoleResource {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //账套编号
    private String entCode;

    //角色编号
    private String roleCode;

    //资源编号
    private String resourceCode;
}
