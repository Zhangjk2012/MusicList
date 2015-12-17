package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.TitleBarDao;
import com.musiclist.entity.TitleBar;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:53:10
 */
@Service
public class TitleBarService {
    
    @Resource
    private TitleBarDao titleBarDao;
    
    public Integer saveTitleBar(TitleBar bar) {
        return (Integer) titleBarDao.save(bar);
    }
    
    public void setTitleBarDao(TitleBarDao titleBarDao) {
        this.titleBarDao = titleBarDao;
    }

    public void updateTitleBar(TitleBar song) {
        titleBarDao.update(song);
    }

    public int removeTitleBar(int id) {
        return titleBarDao.remove(id);
    }

    public List<Object[]> getTitleBars(int rows, int page) {
        return titleBarDao.getTitleBars(rows,page);
    }

    public Long getCount() {
        return titleBarDao.getTotalCount(TitleBar.class);
    }
    
    
}
