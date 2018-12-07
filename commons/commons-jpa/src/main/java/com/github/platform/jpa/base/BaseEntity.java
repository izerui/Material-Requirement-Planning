package com.github.platform.jpa.base;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //版本号
    @Version
    protected int version;

    //创建者
    @Column(updatable = false)
    protected String creator;

    //创建日期
    @Column(updatable = false)
    protected Date createTime = new Date();

    //修改者
    protected String updator;

    //修改日期
    protected Date updateTime;

}
