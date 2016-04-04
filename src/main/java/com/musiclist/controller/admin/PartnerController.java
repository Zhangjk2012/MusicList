package com.musiclist.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class PartnerController {
	
	@RequestMapping("topartner")
	public String toPartner() {
		return "admin/partner";
	}
	
}
