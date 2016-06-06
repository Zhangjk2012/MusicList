package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;

/**  
 * @author ZJK
 * @date 2015年12月17日 上午10:48:43
 */
@Repository
public class SongListDao extends BaseDao {
    
    public Integer save(SongList songList) {
        return (Integer) getSession().save(songList);
    }

    public Long getCount() {
        String hql = "select count(*) from SongList";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<SongList> getSongLists(int page, int rows) {
        String hql = "From SongList s order by s.enable desc,s.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public int remove(int id) {
        String hql = "delete SongList s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    /**
     * 移出榜单对应歌曲
     * @param id
     * @return
     */
    public int removeSong(int id) {
        String hql = "delete SongListSongs s where s.songListId=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getSongListIdAndName() {
        String hql = "select s.id,s.name From SongList s where s.enable=true order by s.id desc";
        return getSession().createQuery(hql).list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getSongListSongs(int songListId) {
    	String hql = "select s,sl.order,sl.id From SongListSongs sl,Song s where s.id=sl.songId and sl.songListId=:songListId order by sl.order asc nulls last, sl.id desc";
        return getSession().createQuery(hql).setInteger("songListId", songListId).list();
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
    		hql = "From Song s where not exists (select 1 from SongListSongs a where a.songId=s.id) order by s.id desc";
    	} else {
    		hql = "From Song s where s.songName like '%"+songName+"%' and not exists (select 1 from SongListSongs a where a.songId=s.id) order by s.id desc";
    	}
    	
    	return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public Long getSelectSongsCount(String songName) {
    	String hql = "";
    	if (songName == null || songName.equals("")) {
    		hql = "select count(*) From Song s where not exists (select 1 from SongListSongs a where a.songId=s.id) order by s.id desc";
    	} else {
    		hql = "select count(*) From Song s where s.songName like '%"+songName+"%' and not exists (select 1 from SongListSongs a where a.songId=s.id) order by s.id desc";
    	}
    	return (Long) getSession().createQuery(hql).uniqueResult();
    }

	public int addSongs2Album(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	public void removeSongBySongId(int songId) {
		String hql = "delete SongListSongs s where s.songId=:id";
        getSession().createQuery(hql).setInteger("id", songId).executeUpdate();
	}

	public int updateSongOrder(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}
}
