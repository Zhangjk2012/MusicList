package com.musiclist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SingerController {
	
	@RequestMapping("singer")
	public String test() {
		return "admin/singer";
	}
	
}
