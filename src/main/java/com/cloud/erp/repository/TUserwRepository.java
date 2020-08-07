package com.cloud.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.erp.pojo.entity.TUserwDO;

public interface TUserwRepository extends JpaRepository<TUserwDO, Integer> {

    TUserwDO findByFName(String fName) throws Exception;
}
