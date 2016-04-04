package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.entity.Album;

@Repository
public class NewAlbumDao extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public List<Album> popularAlbums(int rows, int page) {
		String hql = "select a from Album a , NewAlbum n where a.id = n.albumId and n.flag = true";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}
	
	public Long popularAlbumsCount() {
		String hql = "select count(1) from NewAlbum n where n.flag = true";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	public Long selectAlbumsCount(String albumName) {
		String hql = "select count(1) from Album a where not exists (select 1 from NewAlbum n where n.albumId = a.id)";
		if (albumName != null && !albumName.equals("")) {
			hql += " and a.name like '%"+albumName+"%'";
		}
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Album> selectAlbums(String albumName, int rows, int page) {
		String hql = "select a from Album a where not exists (select 1 from NewAlbum n where n.albumId = a.id)";
		if (albumName != null && !albumName.equals("")) {
			hql += " and a.name like '%"+albumName+"%'";
		}
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}

	public void setAlbums(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	public void removeAlbum(String albumId) {
		getSession().createSQLQuery("delete from music_new_album where album_id = :id").setString("id", albumId).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> rockAlbums(int rows, int page) {
		String hql = "select a from Album a , NewAlbum n where a.id = n.albumId and n.flag = false";
		int skip = rows*(page-1);
		return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
	}
	
	public Long rockAlbumsCount() {
		String hql = "select count(1) from NewAlbum n where n.flag = false";
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
}
