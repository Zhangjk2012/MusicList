package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.RadioStation;

@Repository
public class RadioStationDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<RadioStation> getRadioStationList(int rows, int page) {
		String hql = "from RadioStation order by enable desc, id desc";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount() {
		String hql = "select count(*) from RadioStation";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public int remove(int id) {
        String hql = "delete RadioStation r where r.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
	
}
