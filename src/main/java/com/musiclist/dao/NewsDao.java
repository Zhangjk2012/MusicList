package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.News;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:52:34
 */
@Repository
public class NewsDao extends BaseDao{

    public int remove(int id) {
        String hql = "delete News n where n.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<News> getNewsList(int rows, int page) {
        String hql = "From News";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
}
