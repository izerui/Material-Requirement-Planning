package com.github.platform.rbac.entity;

import com.github.platform.jpa.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//资源
@Data
@Entity
@Table(name = "resource")
public class Resource extends BaseEntity {

    //资源编号
    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String resourceCode;

    //父级资源编号
    private String parentResourceCode;

    //资源名称
    private String resourceName;

    //module:模块 menu:菜单 button:操作
    private String resourceType;

    //权限控制许可URL
    private String permissionUrl;

    //点击跳转URL
    private String url;

    //排序
    private int sort;

    //样式图标
    private String style;

}
