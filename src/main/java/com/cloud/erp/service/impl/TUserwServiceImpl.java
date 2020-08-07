package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.erp.pojo.entity.TUserwDO;
import com.cloud.erp.repository.TUserwRepository;
import com.cloud.erp.service.TUserwService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, DataAccessException.class})
public class TUserwServiceImpl extends AbstractBaseService implements TUserwService {

    @Autowired
    private TUserwRepository tUserwRepository;

    /**
     * @Description: 查询所有
     * @Param: []
     * @return: java.util.List<com.cloud.erp.model.TUserwDO>
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    @Override
    public List<TUserwDO> findAll() throws Exception {
        return tUserwRepository.findAll();
    }

    /**
     * @Description: 根据fName查询
     * @Param: []
     * @return: com.cloud.erp.model.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    @Override
    public Optional<TUserwDO> findByName(String fName) throws Exception {
        return tUserwRepository.findByName(fName).stream().findAny();
    }
}
