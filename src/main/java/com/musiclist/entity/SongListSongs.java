package com.musiclist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 榜单歌曲
 * @author Miner
 */
@Entity
@Table(name="music_song_list_song")
public class SongListSongs {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="song_id", nullable = false, unique = true)
	private Integer songId;
	
	@Column(name="song_list_id", nullable = false) 
	private Integer songListId;
	 
	@Column(name="vote_num")
	private Integer voteNum = null;
	
	public Integer getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}

	@Column(name="song_order")
	private Integer order = null;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSongId() {
		return songId;
	}

	public void setSongId(Integer songId) {
		this.songId = songId;
	}

	public Integer getSongListId() {
		return songListId;
	}

	public void setSongListId(Integer songListId) {
		this.songListId = songListId;
	}
}
