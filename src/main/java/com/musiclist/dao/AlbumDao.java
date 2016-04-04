package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.Album;
import com.musiclist.entity.Song;

/**  
 * @author ZJK
 * @date 2015年12月16日 上午11:43:54
 */
@Repository
public class AlbumDao extends BaseDao{
    
    public Integer save(Album album) {
        return (Integer) getSession().save(album);
    }
    
    public Long getCount() {
        String hql = "select count(*) from Album";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }
    
    public int remove(int id) {
        String hql = "delete Album s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    public int removeAlbumSongs(int id) {
    	String hql = "delete AlbumSongs a where a.albumId=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    public void removeNewAlbum(int id) {
    	String hql = "delete NewAlbum n where n.albumId=:id";
        getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<Album> getAlbums(int page,int rows) {
        String hql = "From Album a order by a.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getAlbumIdAndName(int singer) {
        String hql = "select s.id,s.name From Album s where s.singer=:id order by s.id desc";
        return getSession().createQuery(hql).setInteger("id", singer).list();
    }
    
    /**
     * 获取待选歌曲
     * @param songName
     * @param rows
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Song> getSelectSongs(String songName, int rows, int page) {
    	String hql = "";
    	int skip = rows*(page-1);
    	if (songName == null || songName.equals("")) {
    		hql = "From Song s where not exists (select 1 from AlbumSongs a where a.songId=s.id) order by s.id desc";
    	} else {
    		hql = "From Song s where s.songName like '%"+songName+"%' and not exists (select 1 from AlbumSongs a where a.songId=s.id) order by s.id desc";
    	}
    	
    	return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public Long getSelectSongsCount(String songName) {
    	String hql = "";
    	if (songName == null || songName.equals("")) {
    		hql = "select count(*) From Song s where not exists (select 1 from AlbumSongs a where a.songId=s.id) order by s.id desc";
    	} else {
    		hql = "select count(*) From Song s where s.songName like '%"+songName+"%' and not exists (select 1 from AlbumSongs a where a.songId=s.id) order by s.id desc";
    	}
    	
    	return (Long) getSession().createQuery(hql).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Song> getSelectSongsByAlbumId(String albumId) {
    	String hql = "select s From Song s,AlbumSongs a where s.id=a.songId and a.albumId = :albumId";
    	return getSession().createQuery(hql).setString("albumId", albumId).list();
    }
    
    public void deleteSelectSong(int songId) {
    	getSession().createQuery("delete AlbumSongs a where a.songId=:songId").setInteger("songId", songId).executeUpdate();
    }

	public int addSongs2Album(String hql) {
		return getSession().createSQLQuery(hql).executeUpdate();
	}
    
}
