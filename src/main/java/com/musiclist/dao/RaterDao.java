package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.Rater;

@Repository
public class RaterDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Rater> getRaterList(int rows, int page) {
		String hql = "from Rater order by id desc";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount() {
		String hql = "select count(*) from Rater";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public int remove(int id) {
        String hql = "delete Rater r where r.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
	
}
