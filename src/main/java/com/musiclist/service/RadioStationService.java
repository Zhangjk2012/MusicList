package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.RadioStationDao;
import com.musiclist.entity.RadioStation;

@Service
public class RadioStationService {

	@Resource
	private RadioStationDao radioStationDao;
	
	public void save(RadioStation radioStation) {
		radioStationDao.save(radioStation);
	}

	public List<RadioStation> getRadioStationList(int rows, int page) {
		return radioStationDao.getRadioStationList(rows, page);
	}

	public Long getCount() {
		return radioStationDao.getCount();
	}

	public int removeRadioStation(int id) {
		return radioStationDao.remove(id);
	}

	public void updateRadioStation(RadioStation radioStation) {
		radioStationDao.update(radioStation);
	}

}
