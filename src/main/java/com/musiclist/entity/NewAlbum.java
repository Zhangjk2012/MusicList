package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 新碟上架设置
 * @author Miner
 *
 */
@Entity
@Table(name="music_new_album")
public class NewAlbum {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "album_id")
	private Integer albumId;
	
	/** 流行、摇滚标识,true为流行，false为摇滚 */
	@Column(name="flag",nullable=false)
	private Boolean flag;

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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
}
