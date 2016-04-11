package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.ListIntroductionDao;
import com.musiclist.entity.ListIntroduction;

@Service
public class ListIntroductionService {

	@Resource
	private ListIntroductionDao listIntroductionDao;
	
	public void save(ListIntroduction listIntroduction) {
		listIntroductionDao.save(listIntroduction);
	}

	public List<ListIntroduction> getListIntroductionList(int rows, int page) {
		return listIntroductionDao.getListIntroductionList(rows, page);
	}

	public Long getCount() {
		return listIntroductionDao.getCount();
	}

	public int removeListIntroduction(int id) {
		return listIntroductionDao.remove(id);
	}

	public void updateListIntroduction(ListIntroduction listIntroduction) {
		listIntroductionDao.update(listIntroduction);
	}

	public void setListIntroductionDao(ListIntroductionDao listIntroductionDao) {
		this.listIntroductionDao = listIntroductionDao;
	}

}
