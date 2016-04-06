package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.Partner;

@Repository
public class PartnerDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Partner> getPartnerList(int rows, int page) {
		String hql = "from Partner order by enable desc, id desc";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount() {
		String hql = "select count(*) from Partner";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public int remove(int id) {
        String hql = "delete Partner p where p.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
	
}
