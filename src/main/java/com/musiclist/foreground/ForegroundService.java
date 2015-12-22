package com.musiclist.foreground;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.musiclist.entity.Album;
import com.musiclist.entity.Comment;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongCategory;
import com.musiclist.entity.TitleBar;
import com.sun.org.apache.regexp.internal.recompile;

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
    public List<Object[]> getHotSong(int page,int rows) {
        return foregroundDao.getHotSong(page, rows);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object[]> getNewAlbum(int page,int rows) {
        return foregroundDao.getNewAlbum(page, rows);
    }
    
    public Album getAlbum(int id) {
        return (Album) foregroundDao.load(Album.class, id);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<SongCategory> getSongCategory() {
        return foregroundDao.getSongCategory();
    }
    
    public Object[] getSongInfo(int id) {
    	return foregroundDao.getSongInfo(id);
    }
    
    public List<Song> getSongList(int categoryId) {
        return foregroundDao.getSongByCategory(categoryId);
    }
    
    public Long getSongCount(Integer category) {
        return foregroundDao.getSongCount(category);
    }
    
    public Long getCommentCountById(Integer id) {
        return foregroundDao.getCommentCountById(id);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public Integer saveComment(Comment comment) {
        return (Integer) foregroundDao.save(comment);
    }
    
    public List<Comment> getCommentsById(int id,int rows,int page) {
        return foregroundDao.getCommentsById(id, rows, page);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateCommentSupportNumById(int id) {
        return foregroundDao.addSupportNum(id);
    }
    
    public String getSingerNameById(int id) {
        return foregroundDao.getSingerName(id);
    }
    
    public void setForegroundDao(ForegroundDao foregroundDao) {
        this.foregroundDao = foregroundDao;
    }
    
}
