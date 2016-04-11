package com.musiclist.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.ContactInformation;
import com.musiclist.service.ContactInfoService;

@Controller
@RequestMapping("admin")
public class ContactInfoController {
	
	@Resource
	private ContactInfoService contactInfoService;
	

	@RequestMapping("tocontactinfo")
	public String toContactInfo(ModelMap model) {
		ContactInformation info = contactInfoService.getContactInformation();
		model.addAttribute("info",info);
		return "admin/contactinfo";
	}
	
	@RequestMapping("addcontactInfo")
    public @ResponseBody String addContactInfo(ContactInformation contactInfo) {
		JSONObject jo = new JSONObject();
        try {
        	int id = contactInfoService.saveOrUpdate(contactInfo);
        	jo.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toJSONString();
    }
	
	@RequestMapping("removecontactInfo")
	public @ResponseBody String deleteContactInfo(int id) {
		JSONObject jo = new JSONObject();
		try {
			contactInfoService.removeContactInfo(id);
			jo.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("success", false);
		}
		return jo.toJSONString();
	}
	
}
