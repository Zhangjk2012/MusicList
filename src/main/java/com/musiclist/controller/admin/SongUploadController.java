package com.musiclist.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/uploadSong"	)
	public  @ResponseBody String uploadSong(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
	    
	    String path = request.getSession().getServletContext().getRealPath("/");
	    String absolutPath = "";
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
					if (ext.equalsIgnoreCase("mp4")) {
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
					}
				}
			}
		}
		JSONObject o = new JSONObject();
		o.put("path", absolutPath);
		o.put("flag", extRt);
		return o.toJSONString();
	}
	
}

