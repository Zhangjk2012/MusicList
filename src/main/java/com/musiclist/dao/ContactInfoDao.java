package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.ContactInformation;

@Repository
public class ContactInfoDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<ContactInformation> getContactInformationList(int rows, int page) {
		String hql = "from ContactInformation order by id desc";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public Long getCount() {
		String hql = "select count(*) from ContactInformation";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public int remove(int id) {
        String hql = "delete ContactInformation c where c.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

	public ContactInformation getContactInformation() {
		try {
			String hql = "from ContactInformation";
			return (ContactInformation) getSession().createQuery(hql).uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
