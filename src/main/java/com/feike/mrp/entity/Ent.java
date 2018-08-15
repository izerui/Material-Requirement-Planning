package com.feike.mrp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//账套
@Data
@Entity
@Table(name = "ent")
public class Ent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private Date createTime = new Date();
    private Date updateTime;
    private int recordStatus;
    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String entCode;
    private String entName;
}
