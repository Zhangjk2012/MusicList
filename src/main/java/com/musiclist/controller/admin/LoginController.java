package com.musiclist.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musiclist.config.Config;
import com.musiclist.entity.SysUser;
import com.musiclist.service.LoginService;
import com.musiclist.utils.MD5Util;

/**  
 * @author ZJK
 * @date 2015年12月14日 下午4:15:22
 */
@Controller
@RequestMapping("admin")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @RequestMapping(value="/login")
    public String login(String username,String password,HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) {
        if (username == null || username.equals("") || password == null || password.equals("")) {
            System.out.println("用户 名、密码不能为空！");
            model.addAttribute("errorinfo", "用户名、密码不能为空！");
            return "admin/login"; 
        }
        String str = MD5Util.string2MD5(password);
        SysUser u = loginService.getUser(username,str);
        if (u == null) {
            System.out.println("用户名或密码错误！");
            model.addAttribute("errorinfo", "用户名或密码错误！");
            return "admin/login"; 
        } else {
            session.setAttribute(Config.SESSIONCONTEXT, u);
            model.addAttribute("username", username);
            return "redirect:index";
        }
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        System.out.println("Logout!!");
        session.removeAttribute(Config.SESSIONCONTEXT);
        return "redirect:preLogin";
    }
    
    
    @RequestMapping("preLogin")
    public String preLogin() {
        return "admin/login";
    }
    
    @RequestMapping("index")
    public String postLogin(String username,ModelMap model) {
        System.out.println("index");
        model.addAttribute("username", username);
        return "admin/admin";
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    
}
