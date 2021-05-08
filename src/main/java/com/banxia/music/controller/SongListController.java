package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;

import com.banxia.music.pojo.SongList;
import com.banxia.music.service.SongListService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 歌单 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(SongList songList){
        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.save(songList);
        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(SongList songList){

        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.saveOrUpdate(songList);
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
    public Object deleteSongList(Integer id){
        return songListService.removeById(id);
    }

    @RequestMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return songListService.getById(id);
    }

    @RequestMapping(value = "/allSongList",method = RequestMethod.GET)
    public Object allSongList(){
        return songListService.list();
    }


    @RequestMapping(value = "/songListOfTitle", method = RequestMethod.GET)
    public Object songListOfTitle(String title){
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        return songListService.list(wrapper);
    }

    @RequestMapping(value = "/likeTitle", method = RequestMethod.GET)
    public Object likeTitle(String title){
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("title",title);
        return songListService.list(wrapper);
    }

    @RequestMapping(value = "/likeStyle", method = RequestMethod.GET)
    public Object likeStyle(String style){
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("style",style);
        return songListService.list(wrapper);
    }


    @RequestMapping(value = "/updateSongListPic", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/songListPic/"+fileName;

        try {
            avatarFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);

            boolean flag = songListService.updateById(songList);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"上传成功");
                jsonObject.put("pic",storeAvatarPath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }




}

