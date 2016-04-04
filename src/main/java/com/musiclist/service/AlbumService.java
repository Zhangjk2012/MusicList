package com.musiclist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiclist.dao.AlbumDao;
import com.musiclist.dao.SingerDao;
import com.musiclist.entity.Album;
import com.musiclist.entity.Song;

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
    
    public List<Song> getSelectSongs(String songName, int rows, int page) {
    	return albumDao.getSelectSongs(songName,rows,page);
    }
    
    public Long getSelectSongsCount(String songName) {
		return albumDao.getSelectSongsCount(songName);
	}
    
    public int addSongs2Album(String albumId, String[] songsId) {
    	
    	StringBuffer hql = new StringBuffer("insert into music_album_song (album_id,song_id) values ");
    	
    	for (String id : songsId) {
    		hql.append("(").append(albumId).append(",").append(id).append("),");
		}
    	hql.replace(hql.length()-1, hql.length(), ";");
    	return albumDao.addSongs2Album(hql.toString());
    }
    
    public List<Song> getSelectSongsByAlbumId(String albumId) {
    	return albumDao.getSelectSongsByAlbumId(albumId);
    }
    
    public void deleteSelectSong(int songId){
    	albumDao.deleteSelectSong(songId);
    }
    
    
    public String getSingerName(int id) {
        return singerDao.getSingerName(id);
    }
    
    public void updateAlbum(Album album) {
        albumDao.update(album);
    }
    
    public int removeAlbum(int id) {
    	albumDao.removeAlbumSongs(id);
    	albumDao.removeNewAlbum(id);
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
