package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.News;
import com.musiclist.service.NewsService;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:01:19
 */
@Controller
@RequestMapping("admin")
public class NewsController {
    
    @Resource
    private NewsService newsService;
    
    @RequestMapping("tonews")
    public String toNews(){
        return "admin/news";
    }
    
    
    @RequestMapping("addnews")
    public @ResponseBody String addNews(News news,HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) {
        try {
            System.out.println("The id is:"+newsService.saveNews(news));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deletenews")
    public @ResponseBody String deleteNews(int id) {
        JSONObject o = new JSONObject();
        int count = newsService.removeNews(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updatenews")
    public @ResponseBody String updateNews(News news) {
        JSONObject o = new JSONObject();
        try {
            newsService.updateNews(news);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="newslist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewsList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<News> list = newsService.getNewsList(rows, page);
            if (list != null) {
                Long count  = newsService.getCount();
                for (News n : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("id",  n.getId());
                    jo.put("enable", n.isEnable());
                    jo.put("name",  n.getName());
                    jo.put("picture",  n.getPicture());
                    jo.put("subtitle",  n.getSubtitle());
                    jo.put("title",  n.getTitle());
                    jo.put("content", n.getContent());
                    newArray.add(jo);
                }
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }


	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
    
}
