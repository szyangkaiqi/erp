package com.cloud.erp.pojo.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IpInfo implements Serializable {

    private String url;

    private String p;
}
