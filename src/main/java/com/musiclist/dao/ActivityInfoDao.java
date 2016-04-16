package com.musiclist.dao;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.ActivityInfo;

@Repository
public class ActivityInfoDao extends BaseDao {

	public int remove(int id) {
        String hql = "delete ActivityInfo a where a.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

	public ActivityInfo getActivityInfo() {
		try {
			String hql = "from ActivityInfo";
			return (ActivityInfo) getSession().createQuery(hql).uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

}
