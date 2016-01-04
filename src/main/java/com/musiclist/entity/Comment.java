package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;

/**  
 * @author ZJK
 * @date 2015年12月19日 上午10:23:07
 */
@Entity
@Table(name="music_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = -4388671637037534728L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "ip", nullable = false, length = 50)
    private String ip;
    
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "time", length = 19)
    private Date time = new Date();
    
    @Column(name = "support_num")
    private Integer supportNum;
    
    @Column(name = "reply_content")
    private String replyContent;
    
    @Column(name="song_id")
    private Integer song;
    
    /** 类型评论：歌曲评论:1、专辑评论:2、榜单评论:3 */
    @Column(name="comment_type")
    private int type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Integer supportNum) {
        this.supportNum = supportNum;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getSong() {
        return song;
    }

    public void setSong(Integer song) {
        this.song = song;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
