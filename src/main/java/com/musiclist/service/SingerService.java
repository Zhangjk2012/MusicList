package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.SingerDao;
import com.musiclist.entity.Singer;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:04
 */
@Service
public class SingerService {
    
    @Autowired
    private SingerDao singerDao;
    
    public Integer saveSinger(Singer singer) {
        return singerDao.save(singer);
    }
    
    public List<Object[]> getSingerIdAndName() {
        return singerDao.getSingerIdAndName();
    }
    
    public List<Singer> getSingers(int rows,int page) {
        try {
            return singerDao.getSingers(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void updateSinger(Singer singer) {
        singerDao.update(singer);
    }
    
    public int removeSinger(int id) {
        return singerDao.remove(id);
    }
    
    public Long getCount() {
        return singerDao.getCount();
    }
    
    public void setSingerDao(SingerDao singerDao) {
        this.singerDao = singerDao;
    }
    
}
