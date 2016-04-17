package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.TalkShow;
import com.musiclist.service.TalkShowService;

@Controller
@RequestMapping("admin")
public class TalkShowController {
	
    @Resource
    private TalkShowService talkShowService;
    
	@RequestMapping("rockshow")
	public String toRock() {
		return "admin/rockshow";
	}
	
	@RequestMapping("popularshow")
	public String toPopular() {
		return "admin/popularshow";
	}
	
	@RequestMapping("addTalkShow")
    public @ResponseBody String addShow(TalkShow show) {
        try {
            System.out.println("The id is:"+talkShowService.save(show));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="showtalklist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getTalkList(int rows,int page,int type,HttpServletRequest request) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<TalkShow> list = talkShowService.getTalkShows(rows, page,type);
            if (list != null) {
                Long count  = talkShowService.getCount(type);
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
	@RequestMapping("deleteTalkShow")
    public @ResponseBody String deleteShow(int id) {
        JSONObject o = new JSONObject();
        int count = talkShowService.removeShow(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateTalkShow")
    public @ResponseBody String updateShow(TalkShow show) {
        JSONObject o = new JSONObject();
        try {
            talkShowService.updateShow(show);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
}
