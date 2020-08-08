package com.cloud.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.erp.pojo.entity.UserwDO;

public interface TUserwRepository extends JpaRepository<UserwDO, Integer> {

    /**
     * @Description: 根据name查询
     * @Param: [fName]
     * @return: com.cloud.erp.pojo.entity.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    List<UserwDO> findByName(String fName) throws Exception;
}
