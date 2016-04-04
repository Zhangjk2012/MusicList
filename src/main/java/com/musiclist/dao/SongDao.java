package com.musiclist.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.musiclist.entity.Song;

/**  
 * @author ZJK
 * @date 2015年12月17日 上午10:48:43
 */
@Repository
public class SongDao extends BaseDao {
    
    public Integer save(Song song) {
        return (Integer) getSession().save(song);
    }

    public Long getCount() {
        String hql = "select count(*) from Song";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Song> getSongs(int page, int rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT song.id,song.brief_introduction,song.picture,song.song_flag,song.album,song.singer,song.song_category,song.lyric,");
        sb.append("category.name categoryName,s.`name` singerName ,a.`name` albumName, song.song_name,song.song_path,song.new_song,song.vote_num,song.track_length ");
        sb.append(" FROM music_song song");
        sb.append(" LEFT JOIN music_song_category category ON category.id = song.song_category");
        sb.append(" LEFT JOIN music_singer s ON s.id = song.singer");
        sb.append(" LEFT JOIN music_albums a ON a.id = song.album");
        sb.append(" ORDER BY song.new_song DESC,song.vote_num DESC, song.id DESC");
        
        String hql = "from Song s order by s.id desc";
        
        
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public int remove(int id) {
        String hql = "delete Song s where s.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    public int removeAlbumSong(int id) {
    	String hql = "delete AlbumSongs a where a.songId=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    /**
     * 移出榜单对应的歌曲
     * @param id
     * @return
     */
    public int removeSongListSong(int id) {
    	String hql = "delete SongListSongs s where s.songId=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getSongIdAndName() {
        String hql = "select s.id,s.songName From Song s order by s.id desc";
        return getSession().createQuery(hql).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getHotSongs(int page, int rows) {
        String hql = "from Song s where s.hotSong = true order by s.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public Long getHotSongCount() {
        String hql = "select count(*) from Song s where s.hotSong = true";
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getSelectHotSongs(String songName, int page, int rows) {
        String hql = "from Song s where s.hotSong = false order by s.id desc";
        if (songName != null && !songName.equals("")) {
        	hql += " and s.songName like '%"+songName+"%'";
        }
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    public Long getSelectHotSongCount(String songName) {
        String hql = "select count(*) from Song s where s.hotSong = false";
        if (songName != null && !songName.equals("")) {
        	hql += " and s.songName like '%"+songName+"%'";
        }
        Query query = getSession().createQuery(hql);  
        return (Long)query.uniqueResult();
    }

	public void setHotSong(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	public void removeHotSong(String songId) {
		getSession().createSQLQuery("update music_song set hot_song = false where id=:id").setString("id", songId).executeUpdate();
	}
    
}
