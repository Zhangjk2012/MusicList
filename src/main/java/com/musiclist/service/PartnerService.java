package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.PartnerDao;
import com.musiclist.entity.Partner;

@Service
public class PartnerService {
	
	@Resource
	private PartnerDao partnerDao;
	
	public void save(Partner partner) {
		partnerDao.save(partner);
	}

	public List<Partner> getPartnerList(int rows, int page) {
		
		return partnerDao.getPartnerList(rows,page);
	}

	public Long getCount() {
		return partnerDao.getCount();
	}

	public void updatePartner(Partner partner) {
		partnerDao.update(partner);
	}

	public int removePartner(int id) {
		return partnerDao.remove(id);
	}
	
}
