package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Album;
import com.musiclist.service.NewAlbumService;

/**
 * 新碟上架管理
 * @author Miner
 */
@Controller
@RequestMapping("admin/newalbum")
public class NewAlbumController {
	
	@Resource
	private NewAlbumService newAlbumService;
	
	@RequestMapping("topopular")
	public String toPopular() {
		return "admin/popularalbum";
	}
	
	@RequestMapping(value="popularalbums",produces="text/html;charset=UTF-8")
	public @ResponseBody String popularAlbums(int rows, int page) {
		JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Album> list = newAlbumService.getPopularAlbums(rows, page);
            if (list != null) {
                Long count  = newAlbumService.getPopularAlbumsCount();
                for (Album a : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("briefIntroduction", a.getBriefIntroduction());
                    jo.put("id", a.getId());
                    jo.put("name", a.getName());
                    jo.put("publishTime", a.getPublishTime().toString());
                    jo.put("publishCompany", a.getPublishCompany());
                    jo.put("picture", a.getPicture());
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
	
	@RequestMapping(value="selectalbums",produces="text/html;charset=UTF-8")
	public @ResponseBody String selectAlbums(String albumName, int rows, int page) {
		JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Album> list = newAlbumService.getSelectAlbums(albumName, rows, page);
            if (list != null) {
                Long count  = newAlbumService.getSelectAlbumsCount(albumName);
                for (Album a : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("briefIntroduction", a.getBriefIntroduction());
                    jo.put("id", a.getId());
                    jo.put("name", a.getName());
                    jo.put("publishTime", a.getPublishTime().toString());
                    jo.put("publishCompany", a.getPublishCompany());
                    jo.put("picture", a.getPicture());
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
	
	@RequestMapping("setpopularalbums")
	public @ResponseBody String setPopularAlbums(@RequestParam(value="albumIds[]") String[]albumIds) {
		newAlbumService.setPopularAlbums(albumIds);
		return "true";
	}
	
	@RequestMapping("removealbum")
	public @ResponseBody String removeAlbum(String albumId) {
		JSONObject o = new JSONObject();
		newAlbumService.removeAlbum(albumId);
		o.put("success", true);
		return o.toJSONString();
	}
	
	@RequestMapping("torock")
	public String toRock() {
		return "admin/rockalbum";
	}
	
	@RequestMapping(value="rockalbums",produces="text/html;charset=UTF-8")
	public @ResponseBody String rockAlbums(int rows, int page) {
		JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Album> list = newAlbumService.getRockAlbums(rows, page);
            if (list != null) {
                Long count  = newAlbumService.getRockAlbumsCount();
                for (Album a : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("briefIntroduction", a.getBriefIntroduction());
                    jo.put("id", a.getId());
                    jo.put("name", a.getName());
                    jo.put("publishTime", a.getPublishTime().toString());
                    jo.put("publishCompany", a.getPublishCompany());
                    jo.put("picture", a.getPicture());
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
	
	@RequestMapping("setrockalbums")
	public @ResponseBody String setRockAlbums(@RequestParam(value="albumIds[]") String[]albumIds) {
		newAlbumService.setRockAlbums(albumIds);
		return "true";
	}
	
	public void setNewAlbumService(NewAlbumService newAlbumService) {
		this.newAlbumService = newAlbumService;
	}
	
}
