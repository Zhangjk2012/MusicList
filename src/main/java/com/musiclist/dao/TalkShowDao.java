package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.TalkShow;

@Repository
public class TalkShowDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<TalkShow> getTalkShows(int rows, int page, int type) {
		String hql = "From TalkShow where type=:type order by enable desc, id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setInteger("type", type).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount(int type) {
		String hql = "select count(*) From TalkShow where type=:type";
        return (Long) getSession().createQuery(hql).setInteger("type", type).uniqueResult();
	}

	public int removeShow(int id) {
		return getSession().createQuery("delete from TalkShow where id =:id").setInteger("id", id).executeUpdate();
	}

}
