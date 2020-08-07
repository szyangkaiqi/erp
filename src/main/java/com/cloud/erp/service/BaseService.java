package com.cloud.erp.service;

/**
 * @author YANGKAIQI1
 */
public interface BaseService {
    /**
     * @Description: 清楚缓存
     * @Param: []
     * @return: void
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    void clear() throws Exception;

    /**
     * @Description: flush
     * @Param: []
     * @return: void
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    void flush() throws Exception;
}
