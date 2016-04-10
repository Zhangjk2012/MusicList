package com.musiclist.foreground;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.dao.BaseDao;
import com.musiclist.entity.Comment;
import com.musiclist.entity.Partner;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;
import com.musiclist.entity.News;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:12:01
 */
@Repository
public class ForegroundDao extends BaseDao {
    
    @SuppressWarnings("unchecked")
    public List<News> getNews() {
        String hql = "From News t where t.enable=true order by t.id desc";
        return getSession().createQuery(hql).setMaxResults(8).setFirstResult(0).list();
    }
    
    public News showNews(int id) {
    	String hql = "From News t where t.id=:id";
        return (News) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
	}
    
    @SuppressWarnings("unchecked")
	public List<Partner> getPartners() {
    	String hql = "From Partner p where p.enable=true order by p.id desc";
        return getSession().createQuery(hql).setMaxResults(8).setFirstResult(0).list();
	}
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getHotSong(int page,int rows) {
        StringBuilder sb = new StringBuilder();
		sb.append("SELECT song.id,song.picture,song.song_flag,s.`name` singerName, song.song_name,song.song_path");
		sb.append(" FROM music_song song");
		sb.append(" LEFT JOIN music_singer s ON s.id = song.singer ");
		sb.append(" WHERE song.new_song = TRUE");
		sb.append(" ORDER BY song.new_song DESC,song.vote_num DESC, song.id DESC");
        int skip = rows*(page-1);
        return getSession().createSQLQuery(sb.toString()).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getSongsByAlbumId(int albumId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT song.id,song.song_flag,s.`name` singerName, song.song_name,song.song_path,song.vote_num,song.track_length");
        sb.append(" FROM music_song song");
        sb.append(" LEFT JOIN music_singer s ON s.id = song.singer ");
        sb.append(" WHERE song.album = :id");
        sb.append(" ORDER BY song.vote_num DESC, song.id DESC");
        return getSession().createSQLQuery(sb.toString()).setInteger("id", albumId).list();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getNewAlbum(int page, int rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.id,a.`name`,a.picture,s.`name` singerName FROM music_albums a ");
        sb.append(" LEFT JOIN music_singer s ON s.id = a.singer");
        sb.append(" ORDER BY a.upload_time DESC");
        int skip = rows*(page-1);
        return getSession().createSQLQuery(sb.toString()).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<SongList> getSongCategory() {
        String hql = "From SongCategory s where s.enable = true order by s.id desc";
        return getSession().createQuery(hql).setMaxResults(3).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getSongByCategory(int categoryId) {
        String hql = "From Song s where s.songCategory = :id order by s.voteNum desc";
        return getSession().createQuery(hql).setInteger("id", categoryId).setMaxResults(10).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllSongByCategory(int categoryId) {
    	StringBuilder sb = new StringBuilder();
        sb.append("SELECT song.id,song.song_flag,s.`name` singerName, song.song_name,song.song_path,song.vote_num,song.track_length");
        sb.append(" FROM music_song song");
        sb.append(" LEFT JOIN music_singer s ON s.id = song.singer ");
        sb.append(" WHERE song.song_category = :id");
        sb.append(" ORDER BY song.vote_num DESC, song.id DESC");
        return getSession().createSQLQuery(sb.toString()).setInteger("id", categoryId).list();
    }
    
    public Object[] getSongInfo(int id) {
    	StringBuilder sb = new StringBuilder();
		sb.append("SELECT song.song_path,song.picture,song.id,song.song_name,song.lyric,song.song_flag,s.name singerName,a.name albumName");
		sb.append(",song.album");		
		sb.append(" from music_song song");
		sb.append(" LEFT JOIN music_singer s ON song.singer = s.id");
		sb.append(" LEFT JOIN music_albums a ON a.id = song.album");
		sb.append(" where song.id=:id");
		return (Object[]) getSession().createSQLQuery(sb.toString()).setInteger("id", id).uniqueResult();
    }
    
    /**
     * 获取音乐总数。
     * @param category  音乐类别
     * @return
     * @Date 2015年12月22日 上午11:20:27
     */
    public Long getSongCount(Integer category) {
        String hql = "select count(*) from Song s";
        Long result = null;
        if (category != null) {
            hql += " s.songCategory = :id";
            result = (Long) getSession().createQuery(hql).setInteger("id", category).uniqueResult();
        } else {
            result = (Long) getSession().createQuery(hql).uniqueResult();
        }
        return result;
    }
    
    public Long getAlbumCount() {
        String hql = "select count(*) from Album a";
        return (Long) getSession().createQuery(hql).uniqueResult();
    }
    
    /**
     * 获取歌曲的评论
     * @param id
     * @return
     * @Date 2015年12月22日 下午2:05:17
     */
    public Long getCommentCountById(int id, int type) {
        String hql = "select count(*) from Comment c where c.song = :id and c.type = :type";
        return (Long) getSession().createQuery(hql).setInteger("id", id).setInteger("type", type).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public List<Comment> getCommentsById(int id,int type, int rows,int page) {
        String hql = "from Comment c where c.song = :id and c.type = :type order by c.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setInteger("id", id).setInteger("type", type).setFirstResult(skip).setMaxResults(rows).list();
    }
    
    public int addSupportNum(int id) {
        String hql = "update music_comment set support_num = support_num +1 where id=:id";
        return getSession().createSQLQuery(hql).setInteger("id", id).executeUpdate();
    }

    public String getSingerName(int id) {
        String hql = "select name from Singer where id=:id";
        return (String) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
    }

    public int addSongSupportNum(int id) {
        String hql = "update music_song set vote_num = vote_num +1 where id=:id";
        return getSession().createSQLQuery(hql).setInteger("id", id).executeUpdate();
    }

}
