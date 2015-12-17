package com.musiclist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.musiclist.utils.MD5Util;

@Controller
public class TestController {
    
    
//    @RequestMapping(path="/index",method=RequestMethod.GET)
    public String test() {
//        Singer bean = new Singer();
//        bean.setBriefIntroduction("@@@@@@@@@@@@@@@@@@@");
//        bean.setName("ZhangJK");
//        bean.setPicture("D:\\123\\123\\123");
//        testService.save(bean);
        return "index";
    }
    
    @RequestMapping(path="/admin",method=RequestMethod.GET)
    public String toIndex() {
        System.out.println("ssss");
        return "index";
    }

    
    public static void main(String[] args) {
        try {
            String admin = "admin";
            System.out.println(MD5Util.string2MD5(admin));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
