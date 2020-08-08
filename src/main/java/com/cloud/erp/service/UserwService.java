package com.cloud.erp.service;

import java.util.List;
import java.util.Optional;

import com.cloud.erp.pojo.dto.UserwDTO;

/**
 * @author YANGKAIQI1
 */
public interface UserwService extends BaseService {
    /**
     * @Description: 查询所有
     * @Param: []
     * @return: java.util.List<com.cloud.erp.model.TUserwDO>
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    List<UserwDTO> findAll() throws Exception;

    /**
     * @Description: 根据fName查询
     * @Param: []
     * @return: com.cloud.erp.model.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    Optional<UserwDTO> findByName(String fName) throws Exception;
}
