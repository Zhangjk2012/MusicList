package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * 音乐类别
 * @author ZJK
 * @date 2015年12月17日 上午10:17:03
 */
@Entity
@Table(name="music_song_category")
public class SongCategory implements Serializable{
    
    private static final long serialVersionUID = 3095225689278140874L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    /** 类别名称 */
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    /** 是否启用 */
    @Column(name = "enable")
    private Boolean enable;
    
    /** 类别对应图片路径 */
    @Column(name = "picture", length = 100)
    private String picture;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    
}
