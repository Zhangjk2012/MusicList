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


@Controller
@RequestMapping("/file")
public class UploadController { 
	
	@RequestMapping("/upload2"	)
	public  @ResponseBody String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				int pre = (int) System.currentTimeMillis();
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					String myFileName = file.getOriginalFilename();
					if(myFileName.trim() !=""){
						System.out.println(myFileName);
						String fileName = "demoUpload" + file.getOriginalFilename();
						String path = "D:/" + fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
					}
				}
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}
		return "myFileName";
	}
	
	@RequestMapping("/toUpload"	) 
	public String toUpload() {
		return "/upload";
	}
	
}

