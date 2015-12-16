package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.Singer;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:54
 */
@Repository
public class SingerDao extends BaseDao{
    
    public Integer save(Singer singer) {
        return (Integer) getSession().save(singer);
    }
    
    public Long getCount() {
        String hql = "select count(*) from Singer";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }
    
    public int remove(int id) {
        String hql = "delete Singer s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<Singer> getSingers(int page,int rows) {
        String hql = "From Singer s order by s.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public String getSingerName(int id) {
        String hql = "select s.name From Singer s where s.id=:id";
        return (String) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getSingerIdAndName() {
        String hql = "select s.id,s.name From Singer s order by s.id desc";
        return getSession().createQuery(hql).list();
    }
    
}
