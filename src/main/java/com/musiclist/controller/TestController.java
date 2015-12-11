package com.musiclist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    
    @RequestMapping(path="/test",method=RequestMethod.GET)
    public String test() {
        return "index";
    }
    
    @RequestMapping(path="/index",method=RequestMethod.GET)
    public String toIndex() {
        System.out.println("ssss");
        return "index";
    }
    
}
