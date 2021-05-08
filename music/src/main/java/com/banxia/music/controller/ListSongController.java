package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.ListSong;
import com.banxia.music.service.ListSongService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     * @param listSong
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(ListSong listSong){
        JSONObject jsonObject = new JSONObject();
        boolean flag = listSongService.save(listSong);
        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object detail(String songListId){
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id",songListId);
        return listSongService.list(wrapper);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateListSong(ListSong listSong){
        JSONObject jsonObject = new JSONObject();
        boolean flag = listSongService.saveOrUpdate(listSong);
        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(String songListId,String songId){

        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id",songId);
        wrapper.eq("song_list_id",songListId);
        return  listSongService.remove(wrapper);
    }





}

