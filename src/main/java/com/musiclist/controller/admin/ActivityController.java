package com.musiclist.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.ActivityInfo;
import com.musiclist.service.ActivityService;

@Controller
@RequestMapping("admin")
public class ActivityController {
	
	@Resource
	private ActivityService activityService;
	

	@RequestMapping("toactivity")
	public String toContactInfo(ModelMap model) {
		ActivityInfo info = activityService.getActivityInfo();
		model.addAttribute("info",info);
		return "admin/activity";
	}
	
	@RequestMapping("addactivity")
    public @ResponseBody String addContactInfo(ActivityInfo info) {
		JSONObject jo = new JSONObject();
        try {
        	int id = activityService.saveOrUpdate(info);
        	jo.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toJSONString();
    }
	
	@RequestMapping("removeactivity")
	public @ResponseBody String deleteContactInfo(int id) {
		JSONObject jo = new JSONObject();
		try {
			activityService.removeActivityInfo(id);
			jo.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("success", false);
		}
		return jo.toJSONString();
	}
	
}
