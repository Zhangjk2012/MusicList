package com.musiclist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:52:34
 */
@Repository
public class TitleBarDao extends BaseDao{

    public int remove(int id) {
        String hql = "delete TitleBar t where t.id=:id";
        return getSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getTitleBars(int rows, int page) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT t.*,s.`name` songName FROM music_title_bar t");
        sb.append(" LEFT JOIN music_song s ON t.song = s.id");
        sb.append(" ORDER BY t.enable DESC,t.id DESC");
        int skip = rows*(page-1);
        return getSession().createSQLQuery(sb.toString()).setMaxResults(rows).setFirstResult(skip).list();
    }

}
