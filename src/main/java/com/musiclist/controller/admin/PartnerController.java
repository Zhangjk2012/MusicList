package com.musiclist.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Partner;
import com.musiclist.service.PartnerService;

@Controller
@RequestMapping("admin")
public class PartnerController {
	
	@Resource
	private PartnerService partnerService;
	
	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	@RequestMapping("topartner")
	public String toPartner() {
		return "admin/partner";
	}
	
	@RequestMapping("addpartner")
    public @ResponseBody String addPartner(Partner partner) {
        try {
        	partnerService.save(partner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
	
	@RequestMapping(value="partnerlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getPartnerList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Partner> list = partnerService.getPartnerList(rows, page);
            if (list != null && list.size()>0 ) {
                Long count  = partnerService.getCount();
                newArray.addAll(list);
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
	
	@RequestMapping("deletepartner")
    public @ResponseBody String deletePartner(int id) {
        JSONObject o = new JSONObject();
        int count = partnerService.removePartner(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updatepartner")
    public @ResponseBody String updatePartner(Partner partner) {
        JSONObject o = new JSONObject();
        try {
        	partnerService.updatePartner(partner);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
	
}
