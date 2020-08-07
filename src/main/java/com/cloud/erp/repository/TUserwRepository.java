package com.cloud.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.erp.pojo.entity.TUserwDO;

public interface TUserwRepository extends JpaRepository<TUserwDO, Integer> {

    /**
     * @Description: 根据name查询
     * @Param: [fName]
     * @return: com.cloud.erp.pojo.entity.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    TUserwDO findByName(String fName) throws Exception;
}
