package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 歌手信息
 * @author Administrator
 */
@Entity
@Table(name="music_singer")
public class Singer implements java.io.Serializable{
    
    private static final long serialVersionUID = 1135655672821396709L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 50)
    /** 歌手名称(组合名称) */
    private String name;
    
    @Column(name = "picture", length = 100)
    /** 歌手(组合)图片(存放路径) */
    private String picture;
    
    @Column(name = "brief_introduction", length = 500)
    /** 歌手(组合)简介 */
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }
}
