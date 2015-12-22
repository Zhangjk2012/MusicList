package com.musiclist.foreground;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Album;
import com.musiclist.entity.Comment;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongCategory;
import com.musiclist.entity.TitleBar;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:09:53
 */
@Controller
public class ForegroundController {
    
    @Resource
    private ForegroundService foregroundService;
    
    @RequestMapping(value="hotmore",produces="text/html;charset=UTF-8")
    public String getHotMoreList(Integer category,ModelMap model) {
        Long count = foregroundService.getSongCount(category);
        model.put("songcount",count);
        return "hotlist";
    }
    
    @RequestMapping(value="hotlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getHotList() {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> songs = foregroundService.getHotSong(1, 8);
            JSONArray ja = new JSONArray();
            if (songs != null) {
            	for (Object[] obj : songs) {
					JSONObject j = new JSONObject();
					j.put("id", obj[0]);
					j.put("picture", obj[1]);
					j.put("songFlag", obj[2]);
					j.put("singer", obj[3]);
					j.put("name", obj[4]);
					j.put("songPath", obj[5]);
					ja.add(j);
				}
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="hotmorelist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getHotMoreList(int rows, int page) {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> songs = foregroundService.getHotSong(page, rows);
            JSONArray ja = new JSONArray();
            if (songs != null) {
                for (Object[] obj : songs) {
                    JSONObject j = new JSONObject();
                    j.put("id", obj[0]);
                    j.put("picture", obj[1]);
                    j.put("songFlag", obj[2]);
                    j.put("singer", obj[3]);
                    j.put("name", obj[4]);
                    j.put("songPath", obj[5]);
                    ja.add(j);
                }
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="titlebars",produces="text/html;charset=UTF-8")
    public @ResponseBody String getTitleBar() {
        JSONObject jo = new JSONObject();
        try {
            List<TitleBar> bars = foregroundService.getTitleBars();
            JSONArray ja = new JSONArray();
            if (bars != null) {
                ja.addAll(bars);
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="songcategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongCategory() {
        JSONObject jo = new JSONObject();
        try {
            List<SongCategory> category = foregroundService.getSongCategory();
            JSONArray ja = new JSONArray();
            if (category != null && category.size() > 0) {
                for (SongCategory sc : category) {
                    JSONArray array = new JSONArray();
                    List<Song> songs= foregroundService.getSongList(sc.getId());
                    if (songs != null) {
                        array.addAll(songs);
                    }
                    JSONObject j = new JSONObject();
                    //TODO:需要添加json字符串。
                    j.put("category", sc);
                    
                }
                ja.addAll(category);
                
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="playmusic",produces="text/html;charset=UTF-8")
    public String playMusic(int id,ModelMap model) {
    	String retPage = "";
    	try {
    		Object[] obj = foregroundService.getSongInfo(id);
    		model.put("songPath", obj[0]);
    		model.put("picture", obj[1]);
    		model.put("id", obj[2]);
    		model.put("name", obj[3]);
    		model.put("lyric", obj[4]);
    		if (obj[4] != null) {
    		    String lyric = (String) obj[4];
    		    if (lyric.length() > 40) {
    		        model.put("lyricFirst", lyric.subSequence(0, 40));
    		        model.put("lyricSecond", lyric.subSequence(40, lyric.length()));
    		    } else {
    		        model.put("lyricFirst", lyric);
    		    }
    		}
    		model.put("singerName", obj[6]);
    		model.put("albumName", obj[7]);
    		model.put("album", obj[8]);
    		Long commentCount = foregroundService.getCommentCountById(id);
    		model.put("commentcount", commentCount);
    		if (obj[5] != null) {
    			Boolean b = (Boolean) obj[5]; 
    			if (b) {
    				retPage = "playmusic";
    			} else {
    			    //TODO:播放MP4
    				retPage = "playmp4";
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return retPage;
    }
    
    /**
     * 获取歌曲的所有评论
     * @return
     * @Date 2015年12月19日 下午3:17:53
     */
    @RequestMapping(value="commentlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getCommentsById(int songId,int rows,int page) {
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        try {
            List<Comment> comments = foregroundService.getCommentsById(songId,rows, page);
            ja.addAll(comments);
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping("addSupportNum")
    public @ResponseBody String addSupportNum(int id) {
        JSONObject jo = new JSONObject();
        try {
            int result = foregroundService.updateCommentSupportNumById(id);
            if (result > 0) {
                jo.put("success", "true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("success", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="addComment")
    public @ResponseBody String addComment(String content,int songId,HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            Comment c = new Comment();
            c.setContent(content);
            String ip = request.getRemoteAddr();
            c.setIp(ip);
            c.setSong(songId);
            c.setSupportNum(0);
            foregroundService.saveComment(c);
            Long commentCount = foregroundService.getCommentCountById(songId);
            jo.put("commentCount", commentCount);
            jo.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("success", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="albumlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewAlbum() {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> albums = foregroundService.getNewAlbum(1, 15);
            JSONArray ja = new JSONArray();
            if (albums != null) {
                for (Object[] obj : albums) {
                    JSONObject j = new JSONObject();
                    j.put("id", obj[0]);
                    j.put("name", obj[1]);
                    j.put("picture", obj[2]);
                    j.put("singerName", obj[3]);
                    ja.add(j);
                }
            }
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value="album",produces="text/html;charset=UTF-8")
    public String turnAlbumPage(int id, ModelMap model) {
        try {
            Album a = foregroundService.getAlbum(id);
            model.put("id", a.getId());
            model.put("name", a.getName());
            model.put("picture", a.getPicture());
            model.put("publishTime", a.getPublishTime());
            model.put("publishCompany", a.getPublishCompany());
            model.put("briefIntroduction", a.getBriefIntroduction());
            String singerName = foregroundService.getSingerNameById(a.getSinger());
            model.put("singerName", singerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "album";
    }
    
    public void replyComment(Comment comment,HttpServletRequest request) {
        
    }
    
    
    public void setForegroundService(ForegroundService foregroundService) {
        this.foregroundService = foregroundService;
    }
    
}
