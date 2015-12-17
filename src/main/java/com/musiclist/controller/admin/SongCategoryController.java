package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.SongCategory;
import com.musiclist.service.SongCategoryService;

/** 
 * 歌曲类别管理 
 * @author ZJK
 * @date 2015年12月17日 上午10:22:45
 */
@Controller
@RequestMapping("admin")
public class SongCategoryController {
    
    @Resource
    private SongCategoryService songCategoryService;
    
    @RequestMapping("songcategory")
    public String toSongCategory() {
        return "admin/songcategory";
    }
    
    @RequestMapping("addSongCategory")
    public @ResponseBody String addSongCategory(SongCategory songCategory) {
        try {
            System.out.println("The id is:"+songCategoryService.saveSongCategory(songCategory));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deleteSongCategory")
    public @ResponseBody String deleteSongCategory(int id) {
        JSONObject o = new JSONObject();
        int count = songCategoryService.removeSongCategory(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateSongCategory")
    public @ResponseBody String updateSongCategory(SongCategory singer) {
        JSONObject o = new JSONObject();
        try {
            songCategoryService.updateSongCategory(singer);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="songcategorylist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongCategoryList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<SongCategory> list = songCategoryService.getSongCategorys(rows, page);
            Long count  = songCategoryService.getCount();
            newArray.addAll(list);
            o.put("rows", newArray);
            o.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="songcategorycombolist",produces="text/html;charset=UTF-8")
    public @ResponseBody String categoryComboList() {
        JSONArray newArray = new JSONArray();  
        try {
            List<Object[]> list = songCategoryService.getSongCategoryIdAndName();
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

    public void setSongCategoryService(SongCategoryService songCategoryService) {
        this.songCategoryService = songCategoryService;
    }
    
}
