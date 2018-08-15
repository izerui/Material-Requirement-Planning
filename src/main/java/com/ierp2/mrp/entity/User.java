package com.ierp2.mrp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//用户
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    //用户编号
    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String userCode;

    //用户名
    private String userName;

    //排序
    private int sort;

    //状态：-1删除 -2离职 0禁用 1正常 2待绑定
    private int recordStatus;

    //账套
    private String entCode;

    //登录账号/手机号
    private String account;

    //密码
    private String password;

    //部门编号
    private String departmentCode;

    //是否管理员
    private int admin;

    //头像
    private String avatar;

    //性别
    private String gender;

}
