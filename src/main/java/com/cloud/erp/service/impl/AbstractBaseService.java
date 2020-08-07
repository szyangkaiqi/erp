package com.cloud.erp.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cloud.erp.service.BaseService;

/**
 * @author YANGKAIQI1
 */
public abstract class AbstractBaseService implements BaseService {
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * @Description: 清楚缓存
     * @Param: []
     * @return: void
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    @Override
    public void clear() throws Exception {
        entityManager.clear();

    }

    /**
     * @Description: flush
     * @Param: []
     * @return: void
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    @Override
    public void flush() throws Exception {
        entityManager.flush();
    }
}
