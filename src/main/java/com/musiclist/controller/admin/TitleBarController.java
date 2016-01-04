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
import com.musiclist.entity.TitleBar;
import com.musiclist.service.TitleBarService;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:01:19
 */
@Controller
@RequestMapping("admin")
public class TitleBarController {
    
    @Resource
    private TitleBarService titleBarService;
    
    @RequestMapping("titlebar")
    public String toTitleBar(){
        return "admin/titlebar";
    }
    
    
    @RequestMapping("addTitleBar")
    public @ResponseBody String addSinger(TitleBar titleBar,HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) {
        try {
            System.out.println("The id is:"+titleBarService.saveTitleBar(titleBar));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deleteTitleBar")
    public @ResponseBody String deleteTitleBar(int id) {
        JSONObject o = new JSONObject();
        int count = titleBarService.removeTitleBar(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateTitleBar")
    public @ResponseBody String updateTitleBar(TitleBar song) {
        JSONObject o = new JSONObject();
        try {
            titleBarService.updateTitleBar(song);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="titlebarlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Object[]> list = titleBarService.getTitleBars(rows, page);
            if (list != null) {
                Long count  = titleBarService.getCount();
                for (Object[] a : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("id",  a[0]);
                    jo.put("enable", a[1]);
                    jo.put("name",  a[2]);
                    jo.put("picture",  a[3]);
                    jo.put("song",  a[4]);
                    jo.put("subtitle",  a[5]);
                    jo.put("title",  a[6]);
                    jo.put("songName",  a[7]);
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
    
}
