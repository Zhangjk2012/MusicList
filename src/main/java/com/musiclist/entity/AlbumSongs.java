package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * 专辑所属音乐
 * @author ZJK
 * @date 2016年3月24日 下午4:54:15
 */
@Entity
@Table(name="music_album_song")
public class AlbumSongs {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @Column(name="album_id", nullable = false)
    private Integer albumId;
    
    @Column(name="song_id", nullable = false, unique = true)
    private Integer songId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }
    
}
