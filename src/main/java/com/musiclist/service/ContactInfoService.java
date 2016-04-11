package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.ContactInfoDao;
import com.musiclist.entity.ContactInformation;

@Service
public class ContactInfoService {

	@Resource
	private ContactInfoDao contactInfoDao;
	
	public int saveOrUpdate(ContactInformation contactInfo) {
		if (contactInfo.getId() != null) {
			contactInfoDao.update(contactInfo);
		} else {
			contactInfoDao.save(contactInfo);
		}
		return contactInfo.getId();
	}

	public ContactInformation getContactInformation() {
		return contactInfoDao.getContactInformation();
	}
	
	public List<ContactInformation> getContactInfoList(int rows, int page) {
		return contactInfoDao.getContactInformationList(rows, page);
	}

	public Long getCount() {
		return contactInfoDao.getCount();
	}

	public int removeContactInfo(int id) {
		return contactInfoDao.remove(id);
	}

	public void updateContactInfo(ContactInformation contactInfo) {
		contactInfoDao.update(contactInfo);
	}

	public void setContactInfoDao(ContactInfoDao contactInfoDao) {
		this.contactInfoDao = contactInfoDao;
	}
	
}
