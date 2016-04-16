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
import com.musiclist.entity.ContactInformation;
import com.musiclist.entity.ListIntroduction;
import com.musiclist.entity.Partner;
import com.musiclist.entity.RadioStation;
import com.musiclist.entity.Rater;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;
import com.musiclist.entity.News;

/**  
 * @author ZJK
 * @date 2015年12月21日 上午10:09:53
 */
@Controller
public class ForegroundController {
    
    @Resource
    private ForegroundService foregroundService;
    /************************* 新闻管理开始 *************************/
    
    /**
     * 获取新闻
     * @return
     */
    @RequestMapping(value="news",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNews() {
        JSONObject jo = new JSONObject();
        try {
            List<News> bars = foregroundService.getNews();
            JSONArray ja = new JSONArray();
            if (bars != null) {
            	for (News n : bars) {
					JSONObject j = new JSONObject();
					j.put("id", n.getId());
					j.put("picture", n.getPicture());
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
    
    /**
     * 显示新闻
     * @param id
     * @return
     */
    @RequestMapping(value="shownews",produces="text/html;charset=UTF-8")
    public String showNews(int id,ModelMap model) {
    	News n = foregroundService.showNews(id);
    	model.put("news", n);
    	return "shownews";
    }
    
    /************************* 获取合作伙伴 *************************/
    @RequestMapping(value="getpartners",produces="text/html;charset=UTF-8")
    public @ResponseBody String getPartners() {
    	JSONObject jo = new JSONObject();
        try {
            List<Partner> p = foregroundService.getPartners();;
            JSONArray ja = new JSONArray();
            if (p != null) {
            	ja.addAll(p);
            }
            jo.put("data", ja);
            jo.put("msg", true);
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", false);
        }
        return jo.toJSONString();
    }
    
    /************************* 热门推荐 *************************/
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
    
    /*********************************** 新碟上架 *********************************/
    /**
     * 获取流行新碟上架
     * @return
     */
    @RequestMapping(value="popularalbumlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewPopularAlbum() {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> albums = foregroundService.getNewAlbum(1, 20,true);
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
    
    @RequestMapping(value="rockalbumlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewRockAlbum() {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> albums = foregroundService.getNewAlbum(1, 20,false);
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
    
    @RequestMapping(value="popularalbumlistmore",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewPopularAlbumMore(int rows, int page) {
        JSONObject jo = new JSONObject();
        try {
            List<Object[]> albums = foregroundService.getNewAlbum(page, rows,true);
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
    
    @RequestMapping(value="rockalbumlistmore",produces="text/html;charset=UTF-8")
    public @ResponseBody String getNewRockAlbumMore(int rows, int page) {
    	JSONObject jo = new JSONObject();
    	try {
    		List<Object[]> albums = foregroundService.getNewAlbum(page, rows,false);
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
    
    /**
     * 进入更多，新碟时，先获取新碟的总数
     * @param model
     * @return
     */
    @RequestMapping(value="getmorenewpopulardisc",produces="text/html;charset=UTF-8")
    public String getNewPopularDiscList(ModelMap model) {
        Long count = foregroundService.getNewAlbumCount(true);
        model.put("albumcount",count);
        return "newpopulardisc";
    }
    
    @RequestMapping(value="getmorenewrockdisc",produces="text/html;charset=UTF-8")
    public String getNewRockDiscList(ModelMap model) {
        Long count = foregroundService.getNewAlbumCount(false);
        model.put("albumcount",count);
        return "newrockdisc";
    }
    
    /**
     * 获取专辑信息
     * @param id
     * @param model
     * @return
     */
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
                    j.put("trackLength", obj[5]);
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
    
    /******************************* 获取榜单列表 ********************************/
    /**
     * 获取榜单歌曲类别
     * @return
     */
    @RequestMapping(value="songlistcategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongListCategory() {
        JSONObject jo = new JSONObject();
        try {
            List<SongList> songList = foregroundService.getSongListCategory();
            JSONArray ja = new JSONArray();
            if (songList != null && songList.size() > 0) {
                for (SongList sl : songList) {
                	JSONObject categoryJo = new JSONObject();
                	categoryJo.put("name", sl.getName());
                	categoryJo.put("picture", sl.getPicture());
                	categoryJo.put("id", sl.getId());
                    JSONArray array = new JSONArray();
                    List<Song> songs= foregroundService.getSongList(sl.getId());
                    if (songs != null) {
                    	for (Song song : songs) {
                    		JSONObject songJo = new JSONObject();
                    		songJo.put("id", song.getId());
                    		songJo.put("songName", song.getSongName());
                    		songJo.put("songPath", song.getSongPath());
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
    
    @RequestMapping("toplist")
    public String topList(String id, ModelMap model) {
    	model.put("categoryId", id);
        return "toplist";
    }
    
    @RequestMapping(value="getCategory",produces="text/html;charset=UTF-8")
    public @ResponseBody String getCategory() {
		JSONObject jo = new JSONObject();
		try {
			List<SongList> category = foregroundService.getSongListCategory();
			JSONArray ja = new JSONArray();
			if (category != null && category.size() > 0) {
				for (SongList sc : category) {
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
                    j.put("trackLength", obj[5]);
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
    
    /*************************** 电台列表 ******************************/
    
    @RequestMapping("/toradiostation")
    public String goRadioStation() {
    	return "radiostationlist";
    }
    
    @RequestMapping(value="radiostationlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getRadioStations() {
		JSONObject jo = new JSONObject();
		try {
			List<RadioStation> rl = foregroundService.getRadioStations();
			JSONArray ja = new JSONArray();
			if (rl != null && rl.size() > 0) {
				for (RadioStation r : rl) {
					JSONObject categoryJo = new JSONObject();
					categoryJo.put("name", r.getName());
					categoryJo.put("url", r.getUrl());
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
    
    /***********************  榜单背景介绍  *********************/
    @RequestMapping(value="tolistintroduction",produces="text/html;charset=UTF-8")
    public String goMusicIntroduction(ModelMap model) {
    	ListIntroduction li = foregroundService.showIntroduction();
    	model.put("intro", li);
    	return "introduction";
    }
    
    /***********************  评委介绍  *********************/
    @RequestMapping(value="toraterintroduction",produces="text/html;charset=UTF-8")
    public String goRaterIntroduction(ModelMap model) {
    	return "raterlist";
    }
    
    @RequestMapping(value="raters",produces="text/html;charset=UTF-8")
    public @ResponseBody String getRaters() {
		JSONObject jo = new JSONObject();
		try {
			List<Rater> rl = foregroundService.getRaters();
			JSONArray ja = new JSONArray();
			if (rl != null && rl.size() > 0) {
				for (Rater r : rl) {
					JSONObject categoryJo = new JSONObject();
					categoryJo.put("name", r.getName());
					categoryJo.put("id", r.getId());
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
    
    /**
     * 获取评委介绍
     * @param id
     * @return
     */
    @RequestMapping(value="getratercontent",produces="text/html;charset=UTF-8")
    public String getRaterContent(int id,ModelMap model) {
    	Rater r = foregroundService.showRater(id);
    	model.put("rater", r);
    	return "rater";
    }
    
    /***********************  联系我们  *********************/
    @RequestMapping(value="contactus",produces="text/html;charset=UTF-8")
    public String contactUs(ModelMap model) {
    	ContactInformation c = foregroundService.contactUs();
    	model.put("c", c);
    	return "contactus";
    }
    
    public void replyComment(Comment comment,HttpServletRequest request) {
        
    }
    
    
    public void setForegroundService(ForegroundService foregroundService) {
        this.foregroundService = foregroundService;
    }
    
}
