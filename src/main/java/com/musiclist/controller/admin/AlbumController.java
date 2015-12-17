package com.musiclist.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Album;
import com.musiclist.service.AlbumService;

/**  
 * @author ZJK
 * @date 2015年12月16日 下午3:27:10
 */
@Controller
@RequestMapping("admin")
public class AlbumController {
    
    @Resource
    private AlbumService albumService;
    
    @RequestMapping("album")
    public String toAlbum() {
        return "admin/album";
    }
    
    @RequestMapping("addAlbum")
    public @ResponseBody String addAlbum(Album album) {
        try {
            album.setUploadTime(new Date());
            System.out.println("The id is:"+albumService.saveAlbum(album));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deleteAlbum")
    public @ResponseBody String deleteAlbum(int id) {
        JSONObject o = new JSONObject();
        int count = albumService.removeAlbum(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateAlbum")
    public @ResponseBody String updateSinger(Album album) {
        JSONObject o = new JSONObject();
        try {
            albumService.updateAlbum(album);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="albumcombolist",produces="text/html;charset=UTF-8")
    public @ResponseBody String albumComboList(int singer) {
        JSONArray newArray = new JSONArray();  
        try {
            List<Object[]> list = albumService.getAlbumIdAndName(singer);
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
    
    @RequestMapping(value="albumlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getAlbumList(int rows,int page,HttpServletRequest request,HttpServletResponse response) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Album> list = albumService.getAlbums(rows, page);
            if (list != null) {
                Long count  = albumService.getCount();
                for (Album a : list) {
                    String singerName = albumService.getSingerName(a.getSinger());
                    JSONObject jo = new JSONObject();
                    jo.put("briefIntroduction", a.getBriefIntroduction());
                    jo.put("id", a.getId());
                    jo.put("name", a.getName());
                    jo.put("publishTime", a.getPublishTime().toString());
                    jo.put("publishCompany", a.getPublishCompany());
                    jo.put("singer", a.getSinger());
                    jo.put("picture", a.getPicture());
                    jo.put("singerName", singerName);
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

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }
    
}
