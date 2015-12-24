package com.musiclist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.musiclist.config.Config;
import com.musiclist.entity.SysUser;

/**  
 * @author ZJK
 * @date 2015年12月14日 下午3:04:58
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    Logger logger = Logger.getLogger(LoginInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);  
        // 从session 里面获取用户名的信息  
        String context = request.getContextPath();
        Object obj = session.getAttribute(Config.SESSIONCONTEXT);  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆 
        if (obj == null || "".equals(obj.toString())) {  
            logger.warn("没有登录,跳转到登录界面");
            String url = context+"/admin/preLogin";
//            response.sendRedirect(context+"/admin/preLogin");
//            "if(window!=top){top.location.href='"+url+"';} else {window.location.href='"+url+"'}
            response.getWriter().write("<script>"+"if(window!=top){top.location.href='"+url+"';} else {window.location.href='"+url+"'}"+"</script>");
            return false;
        } 
        SysUser u = (SysUser) obj;
        request.setAttribute("username", u.getName());
        return true;
    }
}
