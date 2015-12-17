package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.SongCategory;

/**  
 * @author ZJK
 * @date 2015年12月17日 上午10:48:43
 */
@Repository
public class SongCategoryDao extends BaseDao {
    
    public Integer save(SongCategory songCategory) {
        return (Integer) getSession().save(songCategory);
    }

    public Long getCount() {
        String hql = "select count(*) from SongCategory";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<SongCategory> getSongCategorys(int page, int rows) {
        String hql = "From SongCategory s order by s.enable desc,s.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public int remove(int id) {
        String hql = "delete SongCategory s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getSongCategoryIdAndName() {
        String hql = "select s.id,s.name From SongCategory s where s.enable=true order by s.id desc";
        return getSession().createQuery(hql).list();
    }
    
}
