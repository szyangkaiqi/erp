package com.cloud.erp.pojo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_userw", schema = "dbo", catalog = "erp")
public class UserwDO {

    @Id
    @Column(name = "FID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "FUserID", nullable = true)
    private Integer userId;

    @Basic
    @Column(name = "FName", nullable = true, length = 80)
    private String name;

    @Basic
    @Column(name = "FSID", nullable = true, length = 80)
    private String sid;

    @Basic
    @Column(name = "FForbidden", nullable = true)
    private Integer forbidden;

}
