package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.pojo.entity.TUserwDO;

public interface TUserwService {
    /**
     * @Description: 查询所有
     * @Param: []
     * @return: java.util.List<com.cloud.erp.model.TUserwDO>
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    List<TUserwDO> findAll() throws Exception;

    /**
     * @Description: 根据fName查询
     * @Param: []
     * @return: com.cloud.erp.model.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    TUserwDO findByFName(String fName) throws Exception;
}
