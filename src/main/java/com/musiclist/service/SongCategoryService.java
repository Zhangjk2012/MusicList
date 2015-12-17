package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.SongCategoryDao;
import com.musiclist.entity.SongCategory;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:04
 */
@Service
public class SongCategoryService {
    
    @Autowired
    private SongCategoryDao songCategoryDao;
    
    public Integer saveSongCategory(SongCategory songCategory) {
        return songCategoryDao.save(songCategory);
    }
    
    public List<SongCategory> getSongCategorys(int rows,int page) {
        try {
            return songCategoryDao.getSongCategorys(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Object[]> getSongCategoryIdAndName() {
        return songCategoryDao.getSongCategoryIdAndName();
    }
    
    public void updateSongCategory(SongCategory category) {
        songCategoryDao.update(category);
    }
    
    public int removeSongCategory(int id) {
        return songCategoryDao.remove(id);
    }
    
    public Long getCount() {
        return songCategoryDao.getCount();
    }

    public void setSongCategoryDao(SongCategoryDao songCategoryDao) {
        this.songCategoryDao = songCategoryDao;
    }
    
}
