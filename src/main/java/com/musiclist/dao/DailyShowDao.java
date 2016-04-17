package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.DailyShow;

@Repository
public class DailyShowDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<DailyShow> getDailyShows(int rows, int page) {
		String hql = "From DailyShow order by enable desc, id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public int removeShow(int id) {
		return getSession().createQuery("delete from DailyShow where id =:id").setInteger("id", id).executeUpdate();
	}

}
