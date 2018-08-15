package com.feike.mrp.entity;

import lombok.Data;

import javax.persistence.*;

//用户-角色关系
@Data
@Entity
@Table(name = "user_role")
public class UserRole {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //账套编号
    private String entCode;

    //用户编号
    private String userCode;

    //角色编号
    private String roleCode;
}
