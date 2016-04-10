package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.RadioStation;
import com.musiclist.service.RadioStationService;

@Controller
@RequestMapping("admin")
public class RadioStationController {
	
	@Resource
	private RadioStationService radioStationService;
	
	public void setRadioStationService(RadioStationService radioStationService) {
		this.radioStationService = radioStationService;
	}

	@RequestMapping("toradiostation")
	public String toRadioStation() {
		return "admin/radiostation";
	}
	
	@RequestMapping("addradiostation")
    public @ResponseBody String addRadioStation(RadioStation radioStation) {
        try {
        	radioStationService.save(radioStation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="radiostationlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getRadioStationList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<RadioStation> list = radioStationService.getRadioStationList(rows, page);
            if (list != null) {
                Long count  = radioStationService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
	
	@RequestMapping("deleteradiostation")
    public @ResponseBody String deleteRadioStation(int id) {
        JSONObject o = new JSONObject();
        int count = radioStationService.removeRadioStation(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateradiostation")
    public @ResponseBody String updateRadioStation(RadioStation radioStation) {
        JSONObject o = new JSONObject();
        try {
        	radioStationService.updateRadioStation(radioStation);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
	
}
