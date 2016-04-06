package com.musiclist.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.musiclist.dao.NewsDao;
import com.musiclist.entity.News;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:53:10
 */
@Service
public class NewsService {
    
    @Resource
    private NewsDao newsDao;
    
    public Integer saveNews(News bar) {
        return (Integer) newsDao.save(bar);
    }
    
    public void setTitleBarDao(NewsDao titleBarDao) {
        this.newsDao = titleBarDao;
    }

    public void updateNews(News song) {
        newsDao.update(song);
    }

    public int removeNews(int id) {
        return newsDao.remove(id);
    }

    public List<News> getNewsList(int rows, int page) {
        return newsDao.getNewsList(rows,page);
    }

    public Long getCount() {
        return newsDao.getTotalCount(News.class);
    }
    
    
}
