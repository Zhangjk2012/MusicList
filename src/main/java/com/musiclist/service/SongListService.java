package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.SongListDao;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:04
 */
@Service
public class SongListService {
    
    @Autowired
    private SongListDao songListDao;
    
    public Integer saveSongList(SongList songList) {
        return songListDao.save(songList);
    }
    
    public List<SongList> getSongLists(int rows,int page) {
        try {
            return songListDao.getSongLists(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Object[]> getSongListIdAndName() {
        return songListDao.getSongListIdAndName();
    }
    
    /**
     * 获取榜单歌曲
     * @param songListId
     * @return
     */
    public List<Song> getSongListSongs(int songListId) {
    	return songListDao.getSongListSongs(songListId);
    }
    
    public List<Song> getSelectSongs(String songName, int rows, int page) {
		return songListDao.getSelectSongs(songName, rows, page);
	}

	public Long getSelectSongsCount(String songName) {
		return songListDao.getSelectSongsCount(songName);
	}
    
    public void updateSongList(SongList List) {
        songListDao.update(List);
    }
    
    public int removeSongList(int id) {
    	songListDao.removeSong(id);
        return songListDao.remove(id);
    }
    
    public Long getCount() {
        return songListDao.getCount();
    }

    public int addSongs2SongList(String songListId, String[] songsId) {
		StringBuffer hql = new StringBuffer("insert into music_song_list_song (song_list_id,song_id) values ");
    	
    	for (String id : songsId) {
    		hql.append("(").append(songListId).append(",").append(id).append("),");
		}
    	hql.replace(hql.length()-1, hql.length(), ";");
    	return songListDao.addSongs2Album(hql.toString());
	}
    
    public void deleteSelectSong(int songId) {
    	songListDao.removeSongBySongId(songId);
    }
    
	public void setSongListDao(SongListDao songListDao) {
		this.songListDao = songListDao;
	}

}
