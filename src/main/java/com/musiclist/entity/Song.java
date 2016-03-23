package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * 歌曲(MP3、MP4)
 * @author Administrator
 * @date 2015年12月14日 下午1:18:54
 */
@Entity
@Table(name="music_song")
public class Song implements Serializable{
    
    private static final long serialVersionUID = 5200387360059200318L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    /** 歌手 */
    @Column(name = "singer")
    private String singerName;
    
    /** 专辑名称 */
    @Column(name = "album")
    private String albumName;
    
    /** 图片路径 */
    @Column(name = "picture", length = 100)
    private String picture;
    
    /** 简介 */
    @Column(name = "brief_introduction", length = 500)
    private String briefIntroduction;
    
    /** 歌词 */
    @Column(name = "lyric", length = 1000)
    private String lyric;
    
    /** 文件路径 */
    @Column(name = "song_path", length = 100)
    private String songPath;
    
    /** 文件名称 */
    @Column(name = "song_name", length = 100)
    private String songName;
    
    /** MP3、MP4标志 ,true is MP3,false is MP4*/
    @Column(name = "song_flag")
    private boolean songFlag;
    
    /** 时长 */
    @Column(name="track_length")
    private String trackLength;
    
    /** 是否上架 */
    @Column(name="up_flag")
    private Boolean upFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public boolean isSongFlag() {
        return songFlag;
    }

    public void setSongFlag(boolean songFlag) {
        this.songFlag = songFlag;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public Boolean getUpFlag() {
        return upFlag;
    }

    public void setUpFlag(Boolean upFlag) {
        this.upFlag = upFlag;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
