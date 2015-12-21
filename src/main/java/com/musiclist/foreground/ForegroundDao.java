package com.musiclist.foreground;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musiclist.dao.BaseDao;
import com.musiclist.entity.Album;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongCategory;
import com.musiclist.entity.TitleBar;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:12:01
 */
@Repository
public class ForegroundDao extends BaseDao {
    
    @SuppressWarnings("unchecked")
    public List<TitleBar> getTitleBars() {
        String hql = "From TitleBar t where t.enable=true order by t.id desc";
        return getSession().createQuery(hql).setMaxResults(8).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getHotSong(int page,int rows) {
        String hql = "From Song s where s.newSong = true order by s.id desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }

    @SuppressWarnings("unchecked")
    public List<Album> getNewAlbum(int page, int rows) {
        String hql = "From Album a order by a.uploadTime desc";
        int skip = rows*(page-1);
        return getSession().createQuery(hql).setMaxResults(rows).setFirstResult(skip).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<SongCategory> getSongCategory() {
        String hql = "From SongCategory s where s.enable true order by s.id desc";
        return getSession().createQuery(hql).setMaxResults(3).setFirstResult(0).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Song> getSongByCategory(int categoryId) {
        String hql = "From Song s where s.songCategory = :id order by s.voteNum desc";
        return getSession().createQuery(hql).setInteger("id", categoryId).setMaxResults(10).setFirstResult(0).list();
    }
    
}
