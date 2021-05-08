package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.Singer;
import com.banxia.music.service.SingerService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
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
 * 歌手 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     * @param singer
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSinger(Singer singer){

        System.out.println(singer.toString());
        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.save(singer);

        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"添加成功");
        }else {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 更新歌手
     * @param singer
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSinger(Singer singer){
        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.updateById(singer);

        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"修改成功");

        }else {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除歌手
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSinger(Integer id){
        return singerService.removeById(id);
    }

    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(Integer id){
       return singerService.getById(id);
    }

    /**
     * 查询所有歌手
     * @return
     */
    @RequestMapping(value = "/allSinger", method = RequestMethod.GET)
    public Object allSinger(){
        System.out.println(singerService.list());
        return singerService.list();
    }

    /**
     * 根据姓名进行模糊查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/singerOfName", method = RequestMethod.GET)
    public Object singerOfName(String name){
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();

        wrapper.like("name",name);
        return singerService.list(wrapper);
    }

    /**
     * 根据性别进行查询
     * @param sex
     * @return
     */
    @RequestMapping(value = "/singOfSex", method = RequestMethod.GET)
    public Object singerOfSex(Integer sex){
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("sex", sex);
        return singerService.list(wrapper);
    }

    @RequestMapping(value = "/updateSingerPic", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") Integer id){
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
                + System.getProperty("file.separator") + "singerPic";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/singerPic/"+fileName;

        try {
            avatarFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);

            boolean flag = singerService.updateById(singer);
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

