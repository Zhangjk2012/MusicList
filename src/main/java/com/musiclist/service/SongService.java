package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.SongDao;
import com.musiclist.entity.Song;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:04
 */
@Service
public class SongService {
    
    @Autowired
    private SongDao songDao;
    
    public Integer saveSong(Song song) {
        return songDao.save(song);
    }
    
    public List<Song> getSongs(int rows,int page) {
        try {
            return songDao.getSongs(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Object[]> getSongIdAndName() {
        return songDao.getSongIdAndName();
    }
    
    public void updateSong(Song song) {
        songDao.update(song);
    }
    
    public int removeSong(int id) {
        return songDao.remove(id);
    }
    
    public Long getCount() {
        return songDao.getCount();
    }

    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }
    
}
