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
public class TUserwDO {

    @Id
    @Column(name = "FID", nullable = false)
    private Integer fid;

    @Basic
    @Column(name = "FUserID", nullable = true)
    private Integer fUserId;

    @Basic
    @Column(name = "FName", nullable = true, length = 80)
    private String fName;

    @Basic
    @Column(name = "FSID", nullable = true, length = 80)
    private String fsid;

    @Basic
    @Column(name = "FForbidden", nullable = true)
    private Integer fForbidden;

}
