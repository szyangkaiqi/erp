package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.erp.annotation.HibernateCacheAnnotation;
import com.cloud.erp.pojo.dto.UserwDTO;
import com.cloud.erp.repository.TUserwRepository;
import com.cloud.erp.service.UserwService;
import com.cloud.erp.utils.BeanUtils;

@Service
@HibernateCacheAnnotation
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, DataAccessException.class})
public class UserwServiceImpl extends AbstractBaseService implements UserwService {

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
    public List<UserwDTO> findAll() throws Exception {
        return BeanUtils.copy(tUserwRepository.findAll(), UserwDTO.class);
    }

    /**
     * @Description: 根据fName查询
     * @Param: []
     * @return: com.cloud.erp.model.TUserwDO
     * @Author: YANGKAIQI1
     * @Date: 2020-08-07
     */
    @Override
    public Optional<UserwDTO> findByName(String fName) throws Exception {
        return BeanUtils.copy(tUserwRepository.findByName(fName), UserwDTO.class).stream().findAny();
    }
}
