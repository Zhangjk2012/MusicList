package com.musiclist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.BaseDao;

/**  
 * @author Administrator
 * @date 2015年12月14日 下午2:33:47
 */
@Service
public class TestService {
    
    @Autowired
    private BaseDao baseDao;
    
    public void save(Object bean) {
        baseDao.save(bean);
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
    
}
