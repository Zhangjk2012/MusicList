package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.DailyShowDao;
import com.musiclist.entity.DailyShow;

@Service
public class DailyShowService {

	@Resource
	private DailyShowDao dailyShowDao;
	
	public Integer save(DailyShow show) {
		return (Integer) dailyShowDao.save(show);
	}

	public List<DailyShow> getDailyShows(int rows, int page) {
		return dailyShowDao.getDailyShows(rows,page);
	}

	public Long getCount() {
		return dailyShowDao.getTotalCount(DailyShow.class);
	}
	
	public int removeShow(int id) {
		return dailyShowDao.removeShow(id);
	}

	public void updateShow(DailyShow show) {
		dailyShowDao.update(show);
	}

	public void setDailyShowDao(DailyShowDao dailyShowDao) {
		this.dailyShowDao = dailyShowDao;
	}

}
