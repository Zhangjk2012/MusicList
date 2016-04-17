package com.musiclist.foreground;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.dao.BaseDao;
import com.musiclist.entity.ActivityInfo;
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
		sb.append("SELECT song.id,song.picture,song.song_flag,");
		sb.append(" CASE WHEN ss.`name` = \"\" THEN song.singer ELSE ss.`name` END AS singerName,");
		sb.append(" song.song_name,song.song_path");
		sb.append(" FROM music_song song");
		sb.append(" LEFT JOIN (select s.name,a.song_id from music_singer s, music_album_song a,music_albums ma where s.id = ma.singer and ma.id = a.album_id) as ss on ss.song_id = song.id ");
		sb.append(" WHERE song.hot_song = TRUE");
		sb.append(" ORDER BY song.id DESC");
        int skip = rows*(page-1);
        return getSession().createSQLQuery(sb.toString()).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getSongsByAlbumId(int albumId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT song.id,song.song_flag,");
        sb.append(" CASE WHEN sa.singerName = \"\" THEN song.singer ELSE sa.singerName END AS singerName,");
        sb.append(" song.song_name,song.song_path,song.track_length");
        sb.append(" FROM  music_album_song mas, music_song song,");
        sb.append(" (select s.name as singerName from music_albums a left join music_singer s on s.id = a.singer where a.id = 1) sa ");
        sb.append(" WHERE mas.song_id = song.id AND mas.album_id = :id");
        sb.append(" ORDER BY mas.id DESC");
        return getSession().createSQLQuery(sb.toString()).setInteger("id", albumId).list();
    }

    @SuppressWarnings("unchecked")
    /**
     * 获取新碟上架
     * @param page
     * @param rows
     * @param flag	流行、摇滚标识
     * @return
     */
    public List<Object[]> getNewAlbum(int page, int rows,boolean flag) {
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,a.`name`,a.picture,s.name as singerName from music_new_album na,music_albums a");
        sb.append(" left join music_singer s on s.id = a.singer");
        sb.append(" where na.album_id = a.id and na.flag =:flag ORDER BY na.id DESC");
        int skip = rows*(page-1);
        return getSession().createSQLQuery(sb.toString()).setBoolean("flag", flag).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<SongList> getSongListCategory() {
        String hql = "From SongList s where s.enable = true order by s.id desc";
        return getSession().createQuery(hql).setMaxResults(3).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getSongsByListId(int id) {
        String hql = "select s From Song s,SongListSongs ss where ss.songListId = :id and ss.songId = s.id order by ss.id desc";
        return getSession().createQuery(hql).setInteger("id", id).setMaxResults(10).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllSongByCategory(int categoryId) {
    	StringBuilder sb = new StringBuilder();
        sb.append("SELECT song.id,song.song_flag,");
        sb.append("CASE WHEN ss.singerName = \"\" THEN song.singer ELSE ss.singerName END AS singerName,");
        sb.append(" song.song_name,song.song_path,song.track_length");
        sb.append(" FROM music_song_list_song sl,music_song song");
        sb.append(" LEFT JOIN (SELECT s.`name` AS singerName,a.song_id FROM music_singer s,music_album_song a,music_albums ma WHERE s.id = ma.singer AND ma.id = a.album_id ) AS ss ON ss.song_id = song.id ");
        sb.append(" WHERE song.id = sl.song_id and sl.song_list_id = :id");
        sb.append(" ORDER BY sl.id DESC");
        return getSession().createSQLQuery(sb.toString()).setInteger("id", categoryId).list();
    }
    
    public Object[] getSongInfo(int id) {
    	StringBuilder sb = new StringBuilder();
		sb.append("SELECT song.song_path,song.picture,song.id,song.song_name,song.lyric,song.song_flag,");
		sb.append(" CASE WHEN ss.singerName = \"\" THEN song.singer ELSE ss.singerName END AS singerName,");
		sb.append(" CASE WHEN ss.albumName = \"\" THEN song.album ELSE ss.albumName END AS albumName");
		sb.append(" ,ss.album_id");
		sb.append(" FROM music_song song");
		sb.append(" LEFT JOIN (select s.`name` as singerName,a.song_id,ma.`name` as albumName,a.album_id from music_singer s, music_album_song a,music_albums ma where s.id = ma.singer and ma.id = a.album_id and a.song_id = :songId) as ss on ss.song_id = song.id ");
		sb.append(" where song.id=:id");
		sb.append(" ORDER BY song.id DESC");
		return (Object[]) getSession().createSQLQuery(sb.toString()).setInteger("songId", id).setInteger("id", id).uniqueResult();
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
    
    public Long getNewAlbumCount(boolean flag) {
        String hql = "select count(*) from NewAlbum na where na.flag = :flag";
        return (Long) getSession().createQuery(hql).setBoolean("flag", flag).uniqueResult();
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
    
    @SuppressWarnings("unchecked")
    public List<RadioStation> getRadioStatios() {
        String hql = "From RadioStation r where r.enable = true order by r.id desc";
        return getSession().createQuery(hql).setMaxResults(3).setFirstResult(0).list();
    }

	public ListIntroduction showIntroduction() {
		String hql = "From ListIntroduction l order by l.id desc";
		@SuppressWarnings("unchecked")
		List<ListIntroduction> l = getSession().createQuery(hql).setMaxResults(1).setFirstResult(0).list();
		if (l != null && l.size()>0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rater> getRaters() {
		String hql = "From Rater r order by r.id desc";
        return getSession().createQuery(hql).setMaxResults(3).setFirstResult(0).list();
	}

	public Rater showRater(int id) {
		 String hql = "from Rater where id=:id";
	     return (Rater) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
	}

	public ContactInformation contactUs() {
		String hql = "From ContactInformation";
		@SuppressWarnings("unchecked")
		List<ContactInformation> l = getSession().createQuery(hql).setMaxResults(1).setFirstResult(0).list();
		if (l != null && l.size()>0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public ActivityInfo activity() {
		String hql = "From ActivityInfo";
		@SuppressWarnings("unchecked")
		List<ActivityInfo> l = getSession().createQuery(hql).setMaxResults(1).setFirstResult(0).list();
		if (l != null && l.size()>0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public List<DailyShow> getDailyShows() {
		String hql = "From DailyShow where enable = true order by id desc";
		@SuppressWarnings("unchecked")
		List<DailyShow> l = getSession().createQuery(hql).setMaxResults(7).setFirstResult(0).list();
		return l;
	}

	public List<TalkShow> getTalkShows(int type) {
		String hql = "From TalkShow where enable = true and type = :type order by id desc";
		@SuppressWarnings("unchecked")
		List<TalkShow> l = getSession().createQuery(hql).setInteger("type", type).setMaxResults(5).setFirstResult(0).list();
		return l;
	}

}
