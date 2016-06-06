package com.musiclist.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiclist.entity.Song;
import com.musiclist.entity.SongList;
import com.musiclist.service.SongListService;

/** 
 * 榜单管理 
 * @author ZJK
 * @date 2015年12月17日 上午10:22:45
 */
@Controller
@RequestMapping("admin")
public class SongListController {
    
    @Resource
    private SongListService songListService;
    
    @RequestMapping("songlistmanager")
    public String toSongList() {
        return "admin/songlist";
    }
    
    @RequestMapping("addSongList")
    public @ResponseBody String addSongList(SongList songList) {
        try {
            System.out.println("The id is:"+songListService.saveSongList(songList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    @RequestMapping("deleteSongList")
    public @ResponseBody String deleteSongList(int id) {
        JSONObject o = new JSONObject();
        int count = songListService.removeSongList(id);
        if (count > 0) {
            o.put("success", true);
            return o.toJSONString();
        } else {
            o.put("success", false);
            return o.toJSONString();
        }
    }
    
    @RequestMapping("updateSongList")
    public @ResponseBody String updateSongList(SongList singer) {
        JSONObject o = new JSONObject();
        try {
            songListService.updateSongList(singer);
            o.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            o.put("success", false);
        }
        return o.toJSONString();
    }
    
    /**
     * 歌曲榜单列表
     * @param rows
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value="songlistlist",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSongListList(int rows,int page,HttpServletRequest request) {
        JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<SongList> list = songListService.getSongLists(rows, page);
            Long count  = songListService.getCount();
            newArray.addAll(list);
            o.put("rows", newArray);
            o.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    @RequestMapping(value="songListcombolist",produces="text/html;charset=UTF-8")
    public @ResponseBody String ListComboList() {
        JSONArray newArray = new JSONArray();  
        try {
            List<Object[]> list = songListService.getSongListIdAndName();
            if (list != null) {
                for (Object[] objects : list) {
                    JSONObject o = new JSONObject();
                    o.put("id", objects[0]);
                    o.put("name", objects[1]);
                    newArray.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newArray.toJSONString();
    }
    
    /**
     * 获取榜单歌曲
     * @param songListId
     * @return
     */
    @RequestMapping(value="getSongListSongs",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSelectSongs(int songListId) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Object[]> listObj = songListService.getSongListSongs(songListId);
            if (listObj != null) {
                for (Object[] obj : listObj) {
                	Song s = (Song) obj[0];
                    JSONObject jo = new JSONObject();
                    jo.put("id", s.getId());
                    jo.put("songName", s.getSongName());
                    jo.put("albumName", s.getAlbumName());
                    jo.put("singerName", s.getSingerName());
                    jo.put("songFlag", s.isSongFlag());
                    jo.put("songOrder", obj[1]==null?"":obj[1]);
                    jo.put("songListId", obj[2]==null?"":obj[2]);
                    jo.put("briefIntroduction", s.getBriefIntroduction());
                    jo.put("picture", s.getPicture());
                    newArray.add(jo);
                }
                o.put("rows", newArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }

    /**
     * 获取可选的榜单歌曲
     * @param songName
     * @param rows
     * @param page
     * @return
     */
    @RequestMapping(value="getselectsonglistsongs",produces="text/html;charset=UTF-8")
    public @ResponseBody String getSelectSongList(String songName,int rows, int page) {
    	JSONArray newArray = new JSONArray();  
        JSONObject o = new JSONObject();
        try {
            List<Song> list = songListService.getSelectSongs(songName,rows,page);
            if (list != null) {
                Long count  = songListService.getSelectSongsCount(songName);
                for (Song s : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", s.getId());
                    jo.put("songName", s.getSongName());
                    jo.put("albumName", s.getAlbumName());
                    jo.put("singerName", s.getSingerName());
                    jo.put("songFlag", s.isSongFlag());
                    newArray.add(jo);
                }
                o.put("rows", newArray);
                o.put("total", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o.toJSONString();
    }
    
    @RequestMapping("addSongs2SongList")
    public @ResponseBody String addSongs2Album(String songListId,@RequestParam(value = "songsId[]")String[] songsId) {
    	int ret = songListService.addSongs2SongList(songListId, songsId);
    	if (ret > 0) {
    		return "true";
    	}
    	return "false";
    }
    
    @RequestMapping("deleteSongListSong")
    public @ResponseBody String deleteSongListSong(int songId) {
    	songListService.deleteSelectSong(songId);
    	JSONObject o = new JSONObject();
    	o.put("success", true);
    	return o.toJSONString();
    } 
    
    @RequestMapping("listSongOrder")
    public @ResponseBody String listSongOrder(HttpServletRequest request) {
    	@SuppressWarnings("unchecked")
		Map<Integer,String[]> orderMap = request.getParameterMap();
    	int ret = songListService.updateListSongOrder(orderMap);
    	JSONObject o = new JSONObject();
    	o.put("success", true);
    	o.put("result", ret);
    	return o.toJSONString();
    }
    
	public void setSongListService(SongListService songListService) {
		this.songListService = songListService;
	}

}
