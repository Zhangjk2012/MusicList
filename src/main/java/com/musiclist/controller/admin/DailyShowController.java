package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.DailyShow;
import com.musiclist.service.DailyShowService;

@Controller
@RequestMapping("admin")
public class DailyShowController {
	
    @Resource
    private DailyShowService dailyShowService;
    
	@RequestMapping("dailyshow")
	public String toSong() {
		return "admin/dailyshow";
	}
	
	@RequestMapping("addDailyShow")
    public @ResponseBody String addShow(DailyShow show) {
        try {
            System.out.println("The id is:"+dailyShowService.save(show));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="showlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongList(int rows,int page,HttpServletRequest request) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<DailyShow> list = dailyShowService.getDailyShows(rows, page);
            if (list != null) {
                Long count  = dailyShowService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
	@RequestMapping("deleteShow")
    public @ResponseBody String deleteShow(int id) {
        JSONObject o = new JSONObject();
        int count = dailyShowService.removeShow(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateShow")
    public @ResponseBody String updateShow(DailyShow show) {
        JSONObject o = new JSONObject();
        try {
            dailyShowService.updateShow(show);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
}
