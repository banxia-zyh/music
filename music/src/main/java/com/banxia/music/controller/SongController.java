package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.mapper.SongMapper;
import com.banxia.music.pojo.Singer;
import com.banxia.music.pojo.Song;
import com.banxia.music.service.SongService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 歌曲 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile multipartFile){
        JSONObject jsonObject = new JSONObject();

        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.png";
        String lyric = request.getParameter("lyric").trim();

        if (multipartFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/song/"+fileName;

        try {
            multipartFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setPic(pic);
            song.setIntroduction(introduction);
            song.setLyric(lyric);
            song.setUrl(storeSongPath);
            System.out.println(song);
            boolean flag = songService.save(song);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"保存成功");
                jsonObject.put("avatar",storeSongPath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"保存败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }



    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(Song song){
        JSONObject jsonObject = new JSONObject();
        boolean flag = songService.saveOrUpdate(song);
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
    public Object deleteSong(Integer id){
        return songService.removeById(id);
    }

    @RequestMapping(value = "/updateSongPic", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") Integer id){
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
                + System.getProperty("file.separator") + "songPic";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/songPic/"+fileName;

        try {
            avatarFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatarPath);

            boolean flag = songService.updateById(song);
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

    @RequestMapping(value = "/updateSongUrl", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/song/"+fileName;

        try {
            avatarFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatarPath);

            boolean flag = songService.updateById(song);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"上传成功");
                jsonObject.put("avator",storeAvatarPath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object detail(Integer songId){
        return songService.getById(songId);
    }

    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object allSong(){
        return songService.list();
    }


    @RequestMapping(value = "/singer/detail",method = RequestMethod.GET)
    public Object singerOfSingerId(Integer singerId){
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id",singerId);
        return songService.list(wrapper);
    }

    @RequestMapping(value = "/songOfSongName",method = RequestMethod.GET)
    public Object songOfSongName(String songName){
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("name",songName);
        return songService.list(wrapper);
    }

    @RequestMapping(value = "/likeSongOfName",method = RequestMethod.GET)
    public Object likeSongOfName(String songName){
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("name",songName);
        return songService.list(wrapper);
    }





}

