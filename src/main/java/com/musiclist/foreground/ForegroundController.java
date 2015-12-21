package com.musiclist.foreground;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Album;
import com.musiclist.entity.Comment;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongCategory;
import com.musiclist.entity.TitleBar;
import com.musiclist.foreground.ForegroundService;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:09:53
 */
@Controller
public class ForegroundController {
    
    @Resource
    private ForegroundService foregroundService;
    
    @RequestMapping(value="hotmorelist",produces="text/html;charset=UTF-8")
    public String getHotMoreList() {
        
        return "hotlist";
    }
    
    @RequestMapping(value="hotlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getHotList() {
        JSONObject jo = new JSONObject();
        try {
            List<Song> songs = foregroundService.getHotSong(1, 8);
            JSONArray ja = new JSONArray();
            if (songs != null) {
                ja.addAll(songs);
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="titlebars",produces="text/html;charset=UTF-8")
    public @ResponseBody String getTitleBar() {
        JSONObject jo = new JSONObject();
        try {
            List<TitleBar> bars = foregroundService.getTitleBars();
            JSONArray ja = new JSONArray();
            if (bars != null) {
                ja.addAll(bars);
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="albumlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewAlbum() {
        JSONObject jo = new JSONObject();
        try {
            List<Album> songs = foregroundService.getNewAlbum(1, 15);
            JSONArray ja = new JSONArray();
            if (songs != null) {
                ja.addAll(songs);
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="songcategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongCategory() {
        JSONObject jo = new JSONObject();
        try {
            List<SongCategory> category = foregroundService.getSongCategory();
            JSONArray ja = new JSONArray();
            if (category != null && category.size() > 0) {
                for (SongCategory sc : category) {
                    JSONArray array = new JSONArray();
                    List<Song> songs= foregroundService.getSongList(sc.getId());
                    if (songs != null) {
                        array.addAll(songs);
                    }
                    JSONObject j = new JSONObject();
                    //TODO:需要添加json字符串。
                    j.put("category", sc);
                    
                }
                ja.addAll(category);
                
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    /**
     * 获取歌曲的所有评论
     * @return
     * @Date 2015年12月19日 下午3:17:53
     */
    @RequestMapping(value="commentlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getCommentsById() {
        return null;
    }
    
    public void addComment(Comment comment,HttpServletRequest request) {
        request.getRemoteAddr();
    }
    
    public void replyComment(Comment comment,HttpServletRequest request) {
        
    }
    
    
    public void setForegroundService(ForegroundService foregroundService) {
        this.foregroundService = foregroundService;
    }
    
}
