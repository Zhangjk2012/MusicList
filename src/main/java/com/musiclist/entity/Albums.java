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

/**  
 * 专辑
 * @author Administrator
 * @date 2015年12月14日 上午11:55:02
 */
@Entity
@Table(name="music_albums")
public class Albums implements Serializable{

    private static final long serialVersionUID = 1213776453277483366L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    /** 专辑名称 */
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    /** 发行时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_time", length = 19)
    private Date publishTime;
    
    /** 发行公司 */
    @Column(name = "publish_company", length = 100)
    private String publishCompany;
    
    /** 歌手 */
    @Column(name = "singer")
    private Integer singer;
    
    /** 专辑图片(封面) */
    @Column(name = "picture", length = 100)
    private String picture;
    
    /** 专辑简介 */
    @Column(name = "brief_introduction", length = 500)
    private String briefIntroduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSinger() {
        return singer;
    }

    public void setSinger(Integer singer) {
        this.singer = singer;
    }
    
    

}
