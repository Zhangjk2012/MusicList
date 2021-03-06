package com.musiclist.foreground;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.musiclist.entity.ActivityInfo;
import com.musiclist.entity.Album;
import com.musiclist.entity.Comment;
import com.musiclist.entity.ContactInformation;
import com.musiclist.entity.DailyShow;
import com.musiclist.entity.ListIntroduction;
import com.musiclist.entity.Partner;
import com.musiclist.entity.RadioStation;
import com.musiclist.entity.Rater;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;
import com.musiclist.entity.News;
import com.musiclist.entity.TalkShow;

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
    public List<News> getNews() {
        return foregroundDao.getNews();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public News showNews(int id) {
    	return foregroundDao.showNews(id);
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Partner> getPartners() {
    	return foregroundDao.getPartners();
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object[]> getHotSong(int page,int rows) {
        return foregroundDao.getHotSong(page, rows);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object[]> getNewAlbum(int page,int rows,boolean flag) {
        return foregroundDao.getNewAlbum(page, rows,flag);
    }
    
    public Album getAlbum(int id) {
        return (Album) foregroundDao.load(Album.class, id);
    }
    
    public Long getNewAlbumCount(boolean flag) {
        return foregroundDao.getNewAlbumCount(flag);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<SongList> getSongListCategory() {
        return foregroundDao.getSongListCategory();
    }
    
    public Object[] getSongInfo(int id) {
    	return foregroundDao.getSongInfo(id);
    }
    
    public List<Song> getSongList(int songListId) {
        return foregroundDao.getSongsByListId(songListId);
    }
    
    public List<Object[]> getAllSongByCategory(int categoryId) {
        return foregroundDao.getAllSongByCategory(categoryId);
    }
    
    public Long getSongCount(Integer category) {
        return foregroundDao.getSongCount(category);
    }
    
    public Long getCommentCountById(Integer id, int type) {
        return foregroundDao.getCommentCountById(id,type);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public Integer saveComment(Comment comment) {
        return (Integer) foregroundDao.save(comment);
    }
    
    public List<Comment> getCommentsById(int id,int type,int rows,int page) {
        return foregroundDao.getCommentsById(id,type, rows, page);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateCommentSupportNumById(int id) {
        return foregroundDao.addSupportNum(id);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateSongSupportNumById(int id) {
        return foregroundDao.addSongSupportNum(id);
    }
    
    public List<Object[]> getSongsByAlbumId(int id) {
        return foregroundDao.getSongsByAlbumId(id);
    }
    
    public String getSingerNameById(int id) {
        return foregroundDao.getSingerName(id);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<RadioStation> getRadioStations() {
    	return foregroundDao.getRadioStatios();
    }

    public ListIntroduction showIntroduction() {
    	return foregroundDao.showIntroduction();
	}
    
    public List<Rater> getRaters() {
    	return foregroundDao.getRaters();
    }

	public ContactInformation contactUs() {
		return foregroundDao.contactUs();
	}

    public Rater showRater(int id) {
    	return foregroundDao.showRater(id);
    }
    
    public ActivityInfo activity() {
    	return foregroundDao.activity();
	}
    
    public List<DailyShow> getDailyShows() {
		return foregroundDao.getDailyShows();
	}
    
    public List<TalkShow> getTalkShows(int type) {
		return foregroundDao.getTalkShows(type);
	}
    
    public void setForegroundDao(ForegroundDao foregroundDao) {
        this.foregroundDao = foregroundDao;
    }

}
