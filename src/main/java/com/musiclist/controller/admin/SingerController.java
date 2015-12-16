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
import com.musiclist.entity.Singer;
import com.musiclist.service.SingerService;

/**  
 * @author ZJK
 * @date 2015年12月15日 上午11:27:11
 */
@Controller
@RequestMapping("admin")
public class SingerController {
    
    @Resource
    private SingerService singerService;
    
    @RequestMapping("singer")
    public String test() {
        return "admin/singer";
    }
    
    @RequestMapping("addSinger")
    public @ResponseBody String addSinger(Singer singer,HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) {
        try {
            System.out.println("The id is:"+singerService.saveSinger(singer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deleteSinger")
    public @ResponseBody String deleteSinger(int id) {
        JSONObject o = new JSONObject();
        int count = singerService.removeSinger(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateSinger")
    public @ResponseBody String updateSinger(Singer singer) {
        JSONObject o = new JSONObject();
        try {
            singerService.updateSinger(singer);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="singercombolist",produces="text/html;charset=UTF-8")
    public @ResponseBody String singerComboList() {
        JSONArray newArray = new JSONArray();  
        try {
            List<Object[]> list = singerService.getSingerIdAndName();
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
    
    @RequestMapping(value="singerlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSingerList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Singer> list = singerService.getSingers(rows, page);
            Long count  = singerService.getCount();
            newArray.addAll(list);
            o.put("rows", newArray);
            o.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }
    
}
