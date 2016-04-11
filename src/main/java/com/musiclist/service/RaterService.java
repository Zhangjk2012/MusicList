package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.RaterDao;
import com.musiclist.entity.Rater;

@Service
public class RaterService {

	@Resource
	RaterDao raterDao;
	
	public void save(Rater rater) {
		raterDao.save(rater);
	}

	public List<Rater> getRaterList(int rows, int page) {
		return raterDao.getRaterList(rows, page);
	}

	public Long getCount() {
		return raterDao.getCount();
	}

	public int removeRater(int id) {
		return raterDao.remove(id);
	}

	public void updateRater(Rater rater) {
		raterDao.update(rater);
	}

	public void setRaterDao(RaterDao raterDao) {
		this.raterDao = raterDao;
	}

}
