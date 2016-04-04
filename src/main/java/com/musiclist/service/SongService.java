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
    	songDao.removeAlbumSong(id);
    	songDao.removeSongListSong(id);
        return songDao.remove(id);
    }
    
    public Long getCount() {
        return songDao.getCount();
    }

    public List<Song> getHotSongs(int rows,int page) {
    	return songDao.getHotSongs(page, rows);
    }
    
    public Long getHotSongCount() {
        return songDao.getHotSongCount();
    }
    
    public Long getSelectHotSongCount(String songName) {
        return songDao.getSelectHotSongCount(songName);
    }
    
    public List<Song> getSelectHotSongs(String songName, int rows,int page) {
    	return songDao.getSelectHotSongs(songName, page, rows);
    }
    
    public void setHotSong(String[] songIds) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("update music_song set hot_song = true where");
		for (String id : songIds) {
			sb.append(" id=").append(id).append(" or");
		}
		sb.replace(sb.length()-2, sb.length(), "");
		songDao.setHotSong(sb.toString());
	}
    
    public void removeHotSong(String songId) {
    	songDao.removeHotSong(songId);
	}
    
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

}
