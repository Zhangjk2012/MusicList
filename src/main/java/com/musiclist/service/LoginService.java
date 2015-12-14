package com.musiclist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.LoginDao;
import com.musiclist.entity.SysUser;

/**  
 * @author ZJK
 * @date 2015年12月14日 下午4:20:55
 */
@Service
public class LoginService {
    
    @Autowired
    private LoginDao loginDao;
    
    public SysUser getUser(String name, String password) {
        return loginDao.getUserByNameAndPwd(name, password);
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    
}
