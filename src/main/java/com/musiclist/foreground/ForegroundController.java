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
    
    /**
     * 获取榜单歌曲类别
     * @return
     */
    @RequestMapping(value="songcategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongCategory() {
        JSONObject jo = new JSONObject();
        try {
            List<SongCategory> category = foregroundService.getSongCategory();
            JSONArray ja = new JSONArray();
            if (category != null && category.size() > 0) {
                for (SongCategory sc : category) {
                	JSONObject categoryJo = new JSONObject();
                	categoryJo.put("name", sc.getName());
                	categoryJo.put("picture", sc.getPicture());
                	categoryJo.put("id", sc.getId());
                    JSONArray array = new JSONArray();
                    List<Song> songs= foregroundService.getSongList(sc.getId());
                    if (songs != null) {
                    	for (Song song : songs) {
                    		JSONObject songJo = new JSONObject();
                    		songJo.put("id", song.getId());
                    		songJo.put("songName", song.getSongName());
                    		songJo.put("songPath", song.getSongPath());
                    		songJo.put("voteNum", song.getVoteNum());
                    		array.add(songJo);
						}
                    	categoryJo.put("songs", array);
                    }
                    ja.add(categoryJo);
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
    		    if (lyric.length() > 50) {
    		        model.put("lyricFirst", lyric.subSequence(0, 40));
    		        model.put("lyricSecond", lyric.subSequence(40, lyric.length()));
    		    } else {
    		        model.put("lyricFirst", lyric);
    		    }
    		}
    		model.put("singerName", obj[6]);
    		model.put("albumName", obj[7]);
    		model.put("album", obj[8]);
    		Long commentCount = foregroundService.getCommentCountById(id,1);
    		model.put("commentcount", commentCount);
    		if (obj[5] != null) {
    			Boolean b = (Boolean) obj[5]; 
    			if (b) {
    				retPage = "playmusic";
    			} else {
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
    public @ResponseBody String getCommentsById(int songId,int type,int rows,int page) {
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        try {
            List<Comment> comments = foregroundService.getCommentsById(songId,type,rows, page);
            ja.addAll(comments);
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping("addCommentSupportNum")
    public @ResponseBody String addCommentSupportNum(int id) {
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
    
    /**
     * 歌曲投票
     * @param id
     * @return
     * @Date 2015年12月29日 下午5:36:00
     */
    @RequestMapping("addSongSupportNum")
    public @ResponseBody String addSongSupportNum(int id) {
        JSONObject jo = new JSONObject();
        try {
            int result = foregroundService.updateSongSupportNumById(id);
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
    public @ResponseBody String addComment(String content,int id,int type, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            Comment c = new Comment();
            c.setContent(content);
            String ip = request.getRemoteAddr();
            c.setIp(ip);
            c.setSong(id);
            c.setSupportNum(0);
            c.setType(type);
            foregroundService.saveComment(c);
            Long commentCount = foregroundService.getCommentCountById(id, type);
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
    
    @RequestMapping(value="albumlistmore",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewAlbumMore(int rows, int page) {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> albums = foregroundService.getNewAlbum(page, rows);
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
    
    @RequestMapping(value="getmorenewdisc",produces="text/html;charset=UTF-8")
    public String getNewDiscList(ModelMap model) {
        Long count = foregroundService.getAlbumCount();
        model.put("albumcount",count);
        return "newdisc";
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
            Long commentCount = foregroundService.getCommentCountById(id,2);
            model.put("commentcount", commentCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "album";
    }
    
    @RequestMapping(value="getSongListByAlbumId",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongsByAlbumId(int id) {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> songs = foregroundService.getSongsByAlbumId(id);
            JSONArray ja = new JSONArray();
            if (songs != null) {
                for (Object[] obj : songs) {
                    JSONObject j = new JSONObject();
                    j.put("id", obj[0]);
                    j.put("songFlag", obj[1]);
                    j.put("singerName", obj[2]);
                    j.put("songName", obj[3]);
                    j.put("songPath", obj[4]);
                    j.put("voteNum", obj[5]);
                    j.put("trackLength", obj[6]);
                    ja.add(j);
                }
            }
            jo.put("total", songs.size());
            jo.put("data", ja);
            jo.put("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", "false");
        }
        return jo.toJSONString();
    }
    
    @RequestMapping("toplist")
    public String topList(String id, ModelMap model) {
    	model.put("categoryId", id);
        return "toplist";
    }
    
    @RequestMapping(value="getCategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getCategory() {
		JSONObject jo = new JSONObject();
		try {
			List<SongCategory> category = foregroundService.getSongCategory();
			JSONArray ja = new JSONArray();
			if (category != null && category.size() > 0) {
				for (SongCategory sc : category) {
					JSONObject categoryJo = new JSONObject();
					categoryJo.put("name", sc.getName());
					categoryJo.put("picture", sc.getPicture());
					categoryJo.put("id", sc.getId());
					ja.add(categoryJo);
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
    
    @RequestMapping(value="getSongsByCategoryId",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongsByCategoryId(int id) {
    	JSONObject jo = new JSONObject();
    	try {
    		JSONArray ja = new JSONArray();
    		List<Object[]> songs = foregroundService.getAllSongByCategory(id);
            if (songs != null) {
                for (Object[] obj : songs) {
                    JSONObject j = new JSONObject();
                    j.put("id", obj[0]);
                    j.put("songFlag", obj[1]);
                    j.put("singerName", obj[2]);
                    j.put("songName", obj[3]);
                    j.put("songPath", obj[4]);
                    j.put("voteNum", obj[5]);
                    j.put("trackLength", obj[6]);
                    ja.add(j);
                }
            }
			jo.put("data", ja);
			jo.put("total", songs.size());
			jo.put("msg", "true");
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("msg", "false");
		}
    	return jo.toJSONString();
    }
    
    /**
     * 获取歌曲榜单评论
     * @param id
     * @return
     */
    @RequestMapping("getCategorySongCommentsCount")
    public @ResponseBody String getCategorySongComments(int id) {
    	Long commentCount = foregroundService.getCommentCountById(id,3);
    	JSONObject jo = new JSONObject();
    	jo.put("commentCount", commentCount);
    	jo.put("msg", "true");
        return jo.toJSONString();
    }
    
    public void replyComment(Comment comment,HttpServletRequest request) {
        
    }
    
    
    public void setForegroundService(ForegroundService foregroundService) {
        this.foregroundService = foregroundService;
    }
    
}
