package com.musiclist.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.ActivityInfoDao;
import com.musiclist.entity.ActivityInfo;
import com.musiclist.entity.ContactInformation;

@Service
public class ActivityService {
	
	@Resource
	private ActivityInfoDao activityInfoDao;
	
	public void setActivityInfoDao(ActivityInfoDao activityInfoDao) {
		this.activityInfoDao = activityInfoDao;
	}

	public int saveOrUpdate(ActivityInfo contactInfo) {
		if (contactInfo.getId() != null) {
			activityInfoDao.update(contactInfo);
		} else {
			activityInfoDao.save(contactInfo);
		}
		return contactInfo.getId();
	}

	public ActivityInfo getActivityInfo() {
		return activityInfoDao.getActivityInfo();
	}
	

	public int removeActivityInfo(int id) {
		return activityInfoDao.remove(id);
	}

	public void updateActivityInfo(ContactInformation contactInfo) {
		activityInfoDao.update(contactInfo);
	}

}
