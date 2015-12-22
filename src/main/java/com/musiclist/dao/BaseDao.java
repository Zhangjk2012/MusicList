package com.musiclist.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**  
 * @author Administrator
 * @date 2015年12月14日 下午2:20:02
 */
@Repository
public class BaseDao {

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * gerCurrentSession 会自动关闭session，使用的是当前的session事务
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 根据 id 查询信息
     * @param id
     * @return
     */
    public Object load(Class<?> c, int id) {
        Session session = getSession();
        return session.get(c, id);
    }

    /**
     * 获取所有信息
     * @param c 
     * @return
     */
    public List<?> getAllList(Class<?> c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        return session.createQuery(hql).list();
    }

    /**
     * 获取总数量
     * @param c
     * @return
     */
    public Long getTotalCount(Class<?> c) {
        Session session = getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 保存
     * @param bean 
     */
    public Serializable save(Object bean) {
        try {
            Session session = getSession();
            return session.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新
     * @param bean 
     */
    public void update(Object bean) {
        Session session = getSession();
        session.update(bean);
    }

    /**
     * 删除
     * @param bean 
     */
    public void delete(Object bean) {
        Session session = getSession();
        session.delete(bean);
    }

    /**
     * 根据ID删除
     * @param c 类
     * @param id ID
     */
    public void delete(Class<?> c, String id) {
        Session session = getSession();
        Object obj = session.get(c, id);
        session.delete(obj);
    }

    /**
     * 批量删除
     * @param c 类
     * @param ids ID 集合
     */
    public void delete(Class<?> c, String[] ids) {
        for (String id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }
}
