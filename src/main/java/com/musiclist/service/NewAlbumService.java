package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.NewAlbumDao;
import com.musiclist.entity.Album;

@Service
public class NewAlbumService {
	
	@Resource
	private NewAlbumDao newAlbumDao;

	public List<Album> getPopularAlbums(int rows, int page) {
		return newAlbumDao.popularAlbums(rows, page);
	}
	
	public Long getPopularAlbumsCount() {
		return newAlbumDao.popularAlbumsCount();
	}
	
	public List<Album> getSelectAlbums(String albumName, int rows, int page) {
		return newAlbumDao.selectAlbums(albumName, rows, page);
	}
	
	public Long getSelectAlbumsCount(String albumName) {
		return newAlbumDao.selectAlbumsCount(albumName);
	}
	
	public void setPopularAlbums(String[] albumIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into music_new_album (album_id,flag) values ");
		for (String id : albumIds) {
			sb.append("(").append(id).append(",").append("true)").append(",");
		}
		sb.replace(sb.length()-1, sb.length(), ";");
		newAlbumDao.setAlbums(sb.toString());
	}
	
	public void removeAlbum(String albumId) {
		newAlbumDao.removeAlbum(albumId);
	}
	
	public List<Album> getRockAlbums(int rows, int page) {
		return newAlbumDao.rockAlbums(rows, page);
	}
	
	public Long getRockAlbumsCount() {
		return newAlbumDao.rockAlbumsCount();
	}
	
	public void setRockAlbums(String[] albumIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into music_new_album (album_id,flag) values ");
		for (String id : albumIds) {
			sb.append("(").append(id).append(",").append("false)").append(",");
		}
		sb.replace(sb.length()-1, sb.length(), ";");
		newAlbumDao.setAlbums(sb.toString());
	}
	
	public void setNewAlbumDao(NewAlbumDao newAlbumDao) {
		this.newAlbumDao = newAlbumDao;
	}

}
