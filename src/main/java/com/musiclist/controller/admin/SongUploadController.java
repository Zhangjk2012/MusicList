package com.musiclist.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.musiclist.utils.MD5Util;


@Controller
@RequestMapping("admin/file")
public class SongUploadController { 
	
	@RequestMapping(value = "/uploadSong",produces="text/html;charset=UTF-8")
	public @ResponseBody String uploadSong(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
	    
	    String path = request.getSession().getServletContext().getRealPath("/");
	    String absolutPath = "";
	    String trackLength = "";
	    String result = "";
	    String info = "";
	    String fileName = "";
        String lyric = "";
        String singerName = "";
        String album = "";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String extRt = "";
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					String myFileName = file.getOriginalFilename();
					String ext = myFileName.substring(myFileName.lastIndexOf("."));
					String md5 = MD5Util.getFileMD5String(file.getBytes());
					String newFile = System.currentTimeMillis()+md5+ext;
					if (ext.equalsIgnoreCase(".mp4")) {
					    path += "mp4/"+newFile;
                        absolutPath = "mp4/"+newFile;
                        extRt = "0";
					} else {
					    path += "mp3/"+newFile;
                        absolutPath = "mp3/"+newFile;
                        extRt = "1";
					}
					if(myFileName.trim() !=""){
						File localFile = new File(path);
						file.transferTo(localFile);
						if (extRt.equals("1")) {
						    MP3File mp3File = null;
						    try {
						        mp3File = new MP3File(localFile);
						        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
						        trackLength = audioHeader.getTrackLengthAsString();
						        
						        if (mp3File.hasID3v2Tag()) {
						        	AbstractID3v2Tag tag = mp3File.getID3v2Tag();
						        	AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("TALB");
						        	if (frame != null) {
						        		album = frame.getContent();
						        	}
						        	AbstractID3v2Frame frame1 = (AbstractID3v2Frame) tag.getFrame("TIT2");
						        	if (frame1 != null) {
						        		fileName = frame1.getContent();
						        	}
						        	AbstractID3v2Frame frame3 = (AbstractID3v2Frame) tag.getFrame("TPE1");
						        	if (frame3 != null) {
						        		singerName = frame3.getContent();
						        	}
						        	AbstractID3v2Frame frame2 = (AbstractID3v2Frame) tag.getFrame("TEXT");
						        	if (frame2 != null) {
						        		lyric = frame2.getContent();
						        	}
						        } else if (mp3File.hasID3v1Tag()) {
						        	ID3v1Tag tag = mp3File.getID3v1Tag();
						        	album = tag.getFirst("ALBUM");
						        	fileName = tag.getFirst("TITLE");
						        	singerName = tag.getFirst("ARTIST");
						        }
						        result = "true";
						    } catch (Exception e) {
						        localFile.delete();
						        result = "false";
						        info = "请上传正确的MP3文件。";
						        e.printStackTrace();
						    }  
						} else {
						    result = "true";
						}
					}
				}
			}
		}
		JSONObject o = new JSONObject();
		o.put("path", absolutPath);
		o.put("flag", extRt);
		o.put("trackLength", trackLength);
		o.put("result", result);
		o.put("info", info);
		o.put("songName", fileName);
		o.put("album", album);
		o.put("lyric", lyric);
		o.put("singerName", singerName);
		return o.toJSONString();
	}
	
}

