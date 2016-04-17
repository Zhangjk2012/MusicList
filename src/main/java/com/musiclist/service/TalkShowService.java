package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.TalkShowDao;
import com.musiclist.entity.TalkShow;

@Service
public class TalkShowService {
	
	@Resource
	private TalkShowDao talkShowDao;
	
	public Integer save(TalkShow show) {
		return (Integer) talkShowDao.save(show);
	}

	public List<TalkShow> getTalkShows(int rows, int page, int type) {
		return talkShowDao.getTalkShows(rows, page, type);
	}

	public Long getCount(int type) {
		return talkShowDao.getCount(type);
	}

	public int removeShow(int id) {
		return talkShowDao.removeShow(id);
	}

	public void updateShow(TalkShow show) {
		talkShowDao.update(show);
	}

}
