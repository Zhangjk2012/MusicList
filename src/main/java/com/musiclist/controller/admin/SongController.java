package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Song;
import com.musiclist.service.SongService;

@Controller
@RequestMapping("admin")
public class SongController {
	
    @Resource
    private SongService songService;
    
	@RequestMapping("song")
	public String toSong() {
		return "admin/song";
	}
	
	@RequestMapping("addSong")
    public @ResponseBody String addSinger(Song song,HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) {
        try {
            System.out.println("The id is:"+songService.saveSong(song));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping("deleteSong")
    public @ResponseBody String deleteSong(int id) {
        JSONObject o = new JSONObject();
        int count = songService.removeSong(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateSong")
    public @ResponseBody String updateSong(Song song) {
        JSONObject o = new JSONObject();
        try {
            songService.updateSong(song);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="songlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongList(int rows,int page,HttpServletRequest request) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Song> list = songService.getSongs(rows, page);
            if (list != null) {
                Long count  = songService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="songcombolist",produces="text/html;charset=UTF-8")
    public @ResponseBody String singerComboList() {
        JSONArray newArray = new JSONArray();  
        try {
            List<Object[]> list = songService.getSongIdAndName();
            if (list != null) {
                for (Object[] objects : list) {
                    JSONObject o = new JSONObject();
                    o.put("id", objects[0]);
                    o.put("name", objects[1]);
                    newArray.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newArray.toJSONString();
    }

    @RequestMapping("hotsong/tolist")
	public String toHotSong() {
		return "admin/hotsong";
	}
    
    @RequestMapping(value="hotsong/hotsonglist",produces="text/html;charset=UTF-8")
    public @ResponseBody String hotSongList(int rows,int page,HttpServletRequest request) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Song> list = songService.getHotSongs(rows, page);
            if (list != null) {
                Long count  = songService.getHotSongCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    /**
     * 获取热门推荐歌曲
     * @param rows
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value="hotsong/selecthotsongs",produces="text/html;charset=UTF-8")
    public @ResponseBody String selectHotSongs(String songName, int rows,int page,HttpServletRequest request) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Song> list = songService.getSelectHotSongs(songName, rows, page);
            if (list != null) {
                Long count  = songService.getSelectHotSongCount(songName);
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    @RequestMapping("hotsong/setHotSong")
    public @ResponseBody String setHotSong(@RequestParam(value="songIds[]")String[] songIds) {
    	JSONObject o = new JSONObject();
    	songService.setHotSong(songIds);
    	o.put("success", true);
    	return o.toJSONString();
    }
    
    @RequestMapping("hotsong/removeHotSong")
    public @ResponseBody String removeHotSong(String songId) {
    	JSONObject o = new JSONObject();
    	songService.removeHotSong(songId);
    	o.put("success", true);
    	return o.toJSONString();
    }
    
    public void setSongService(SongService songService) {
        this.songService = songService;
    }
}
