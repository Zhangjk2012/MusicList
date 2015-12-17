package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.Album;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:54
 */
@Repository
public class AlbumDao extends BaseDao{
    
    public Integer save(Album album) {
        return (Integer) getSession().save(album);
    }
    
    public Long getCount() {
        String hql = "select count(*) from Album";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }
    
    public int remove(int id) {
        String hql = "delete Album s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<Album> getAlbums(int page,int rows) {
        String hql = "From Album a order by a.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getAlbumIdAndName(int singer) {
        String hql = "select s.id,s.name From Album s where s.singer=:id order by s.id desc";
        return getSession().createQuery(hql).setInteger("id", singer).list();
    }
    
}
