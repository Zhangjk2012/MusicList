package com.musiclist.controller;

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

import com.musiclist.utils.MD5Util;


@Controller
@RequestMapping("admin/file")
public class UploadController { 
	
	@RequestMapping("/uploadImg"	)
	public  @ResponseBody String uploadImg(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
	    
	    String path = request.getSession().getServletContext().getRealPath("/");
	    String absolutPath = "";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
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
					    
					} else {
					    path += "img/"+newFile;
					    absolutPath = "img/"+newFile;
					}
					if(myFileName.trim() !=""){
						File localFile = new File(path);
						file.transferTo(localFile);
					}
				}
			}
		}
		return absolutPath;
	}
	
	@RequestMapping("deleteImg")
	public String deleteImg(String filePath,HttpServletRequest request) {
	    try {
	        String path = request.getSession().getServletContext().getRealPath("/");
	        filePath = path+filePath;
	        File f = new File(filePath);
	        if (f.exists()) {
	            f.delete();
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return null;
	}
	
}

