package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Rater;
import com.musiclist.service.RaterService;

@Controller
@RequestMapping("admin")
public class RaterController {
	
	@Resource
	private RaterService raterService;
	

	@RequestMapping("torater")
	public String toRater() {
		return "admin/rater";
	}
	
	@RequestMapping("addrater")
    public @ResponseBody String addRater(Rater Rater) {
        try {
        	raterService.save(Rater);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="raterlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getRaterList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Rater> list = raterService.getRaterList(rows, page);
            if (list != null) {
                Long count  = raterService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
	
	@RequestMapping("deleterater")
    public @ResponseBody String deleteRater(int id) {
        JSONObject o = new JSONObject();
        int count = raterService.removeRater(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updaterater")
    public @ResponseBody String updateRater(Rater Rater) {
        JSONObject o = new JSONObject();
        try {
        	raterService.updateRater(Rater);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }

	public void setRaterService(RaterService raterService) {
		this.raterService = raterService;
	}
	
}
