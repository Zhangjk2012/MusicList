package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.AlbumDao;
import com.musiclist.dao.SingerDao;
import com.musiclist.entity.Album;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:04
 */
@Service
public class AlbumService {
    
    @Autowired
    private AlbumDao albumDao;
    
    @Autowired
    private SingerDao singerDao;
    
    public Integer saveAlbum(Album album) {
        return albumDao.save(album);
    }
    
    public List<Object[]> getAlbumIdAndName(int singer) {
        return albumDao.getAlbumIdAndName(singer);
    }
    
    public List<Album> getAlbums(int rows,int page) {
        try {
            return albumDao.getAlbums(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getSingerName(int id) {
        return singerDao.getSingerName(id);
    }
    
    public void updateAlbum(Album album) {
        albumDao.update(album);
    }
    
    public int removeAlbum(int id) {
        return albumDao.remove(id);
    }
    
    public Long getCount() {
        return albumDao.getCount();
    }

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void setSingerDao(SingerDao singerDao) {
        this.singerDao = singerDao;
    }
    
}
