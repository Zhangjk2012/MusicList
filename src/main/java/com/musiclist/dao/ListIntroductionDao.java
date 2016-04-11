package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.ListIntroduction;

@Repository
public class ListIntroductionDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<ListIntroduction> getListIntroductionList(int rows, int page) {
		String hql = "from ListIntroduction order by id desc";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount() {
		String hql = "select count(*) from ListIntroduction";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public int remove(int id) {
        String hql = "delete ListIntroduction l where l.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
	
}
