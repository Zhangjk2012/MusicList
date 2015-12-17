package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @author ZJK
 * @date 2015年12月17日 下午5:07:10
 */
@Entity
@Table(name="music_title_bar")
public class TitleBar implements Serializable{

    private static final long serialVersionUID = 5403727984484073637L;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    /** 名称 */
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    /** 标题 */
    @Column(name = "title", length = 50)
    private String title;
    
    /** 副标题 */
    @Column(name = "subtitle", length = 50)
    private String subtitle;
    
    /** 歌曲 */
    @Column(name = "song", nullable = false)
    private Integer song;
    
    /** 是否启用 */
    @Column(name = "enable")
    private boolean enable;
    
    /** 图片路径 */
    @Column(name = "picture")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getSong() {
        return song;
    }

    public void setSong(Integer song) {
        this.song = song;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
