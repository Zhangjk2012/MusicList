package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.ListIntroduction;
import com.musiclist.service.ListIntroductionService;

@Controller
@RequestMapping("admin")
public class ListIntroductionController {
	
	@Resource
	private ListIntroductionService listIntroductionService;
	

	@RequestMapping("tolistintroduction")
	public String toListIntroduction() {
		return "admin/listintroduction";
	}
	
	@RequestMapping("addlistintroduction")
    public @ResponseBody String addListIntroduction(ListIntroduction ListIntroduction) {
        try {
        	listIntroductionService.save(ListIntroduction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="listintroductionlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getListIntroductionList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<ListIntroduction> list = listIntroductionService.getListIntroductionList(rows, page);
            if (list != null) {
                Long count  = listIntroductionService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
	
	@RequestMapping("deletelistintroduction")
    public @ResponseBody String deleteListIntroduction(int id) {
        JSONObject o = new JSONObject();
        int count = listIntroductionService.removeListIntroduction(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updatelistintroduction")
    public @ResponseBody String updateListIntroduction(ListIntroduction ListIntroduction) {
        JSONObject o = new JSONObject();
        try {
        	listIntroductionService.updateListIntroduction(ListIntroduction);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }

	public void setListIntroductionService(
			ListIntroductionService listIntroductionService) {
		this.listIntroductionService = listIntroductionService;
	}

	
}
