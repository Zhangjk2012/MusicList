package com.musiclist.foreground.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musiclist.entity.Comment;

/**  
 * @author ZJK
 * @date 2015年12月19日 下午3:17:03
 */
@Controller
public class CommentController {
    
    /**
     * 获取歌曲的所有评论
     * @return
     * @Date 2015年12月19日 下午3:17:53
     */
    @RequestMapping(value="commentlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getCommentsById() {
        return null;
    }
    
    public void addComment(Comment comment,HttpServletRequest request) {
        request.getRemoteAddr();
    }
    
    public void replyComment(Comment comment,HttpServletRequest request) {
        
    }
    
}
