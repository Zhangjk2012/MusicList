package com.musiclist.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.SysUser;

/**  
 * @author ZJK
 * @date 2015年12月14日 下午4:22:15
 */
@Repository
public class LoginDao extends BaseDao{
    
    private static Logger log = Logger.getLogger(LoginDao.class);
    
    public SysUser getUserByNameAndPwd(String name,String pwd) {
        try {
            log.info("Get sys user by name and pwd,"+name+" "+pwd);
            String hql = "from SysUser u where u.name = :name and u.password = :pwd";
            Session session = getSession();
            SysUser user = (SysUser) session.createQuery(hql).setString("name", name).setString("pwd", pwd).uniqueResult();
            return user;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
    
}
