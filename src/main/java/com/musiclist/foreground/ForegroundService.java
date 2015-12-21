package com.musiclist.foreground;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.musiclist.entity.Album;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongCategory;
import com.musiclist.entity.TitleBar;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:16:43
 */
@Service
@Transactional
public class ForegroundService {
    
    @Resource
    private ForegroundDao foregroundDao;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<TitleBar> getTitleBars() {
        return foregroundDao.getTitleBars();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Song> getHotSong(int page,int rows) {
        return foregroundDao.getHotSong(page, rows);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Album> getNewAlbum(int page,int rows) {
        return foregroundDao.getNewAlbum(page, rows);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<SongCategory> getSongCategory() {
        return foregroundDao.getSongCategory();
    }
    
    public List<Song> getSongList(int categoryId) {
        return foregroundDao.getSongByCategory(categoryId);
    }
    
    public void setForegroundDao(ForegroundDao foregroundDao) {
        this.foregroundDao = foregroundDao;
    }
    
}
